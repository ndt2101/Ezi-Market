package com.tuan2101.ezimarket.viewmodel

import android.app.Activity
import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.tuan2101.ezimarket.dataclasses.*
import com.tuan2101.ezimarket.service.CallLocationApi
import com.tuan2101.ezimarket.utils.await
import com.tuan2101.ezimarket.utils.notifyObserverInUI
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit


/**
 * Created by ndt2101 on 1/28/2022.
 */
class LoginAndSignupViewModel(application: Application) : AndroidViewModel(application) {

    var navToRegister = MutableLiveData<Boolean>()
    var navToContinueRegister = MutableLiveData(false)
    var activity: Activity? = null
    var navToVerify = MutableLiveData(false)
    val code = MutableLiveData<String>()
    val isSuccess = MutableLiveData<String>()
    lateinit var codeBySystem: String
    var phoneNumber: String = ""
    var region: String = ""
    var password: String = ""
    var user = User()
    lateinit var location: Location
    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    private val bgScope = CoroutineScope(Dispatchers.IO + job)
    val provinceList = MutableLiveData<ArrayList<Province>>(ArrayList())
    val districtList = MutableLiveData<ArrayList<District>>(ArrayList())
    val wardList = MutableLiveData<ArrayList<Ward>>(ArrayList())
    var selectedProvince = MutableLiveData<Province>()
    var selectedDistrict = MutableLiveData<District>()
    var selectedWard = MutableLiveData<Ward>()
    val selectAvatar = MutableLiveData(false)
    var avatarUri = MutableLiveData<Uri>()
    private val backGroundScope = CoroutineScope(Dispatchers.IO + job)

    private val callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                code.value = p0.smsCode
                if (!code.value.isNullOrBlank()) {
                    verifyCode(code.value!!)
                    isSuccess.value = "Đã gửi sms chứa mã OTP"
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                isSuccess.value = p0.message
                Log.e("444", p0.message.toString()) // TODO: toast in ui
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                codeBySystem = p0
                Log.i("codeBySys", p0)
            }
        }


    fun onNavToRegisterFragment() {
        navToRegister.value = true
    }

    fun onSelectAvatar() {
        selectAvatar.value = true
    }

    fun onNavToContinueRegister() {
        navToContinueRegister.value = true
    }

    fun onNavToVerify() {
        navToVerify.value = true
    }

    fun verifyCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(codeBySystem, code)
        uiScope.launch {
            val createdUser = signInWithPhoneAuthCredential(credential)
            if (createdUser != null) {
                val avtUrl = upAvatarFile(createdUser)
                Log.i("777", avtUrl)
                if (avtUrl != "") {
                    saveUser(createdUser, avtUrl)
                }
            }

        }
    }

    private suspend fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential): FirebaseUser? {
        try {
            var createdUser: FirebaseUser? = null
            val authResult: AuthResult? =
                FirebaseAuth.getInstance().signInWithCredential(credential).await()
            // Sign in success, update UI with the signed-in user's information
            if (authResult != null) {
                createdUser = authResult.user
                val phoneUtil = PhoneNumberUtil.getInstance()
                try {
                    val numberProto = phoneUtil.parse(phoneNumber, region)
                    val phoneNum = phoneUtil.format(
                        numberProto,
                        PhoneNumberUtil.PhoneNumberFormat.NATIONAL
                    )
                    val fakeEmail = phoneNum.toString().replace(" ", "") + "@ezi.com"
                    val emailCredential: AuthCredential =
                        EmailAuthProvider.getCredential(fakeEmail, password.trim())
                    createdUser!!.linkWithCredential(emailCredential)
                } catch (e: NumberParseException) {
                    System.err.println("NumberParseException was thrown: $e")
                }
            }
            return createdUser
        } catch (e: Exception) {
            isSuccess.value = "Taọ tài khoản thất bại"
            if (e is FirebaseAuthInvalidCredentialsException) {
                // The verification code entered was invalid
                isSuccess.value = isSuccess.value + ": Nhập sai mã OTP"
            }
        }
        throw Exception("Can not initial account, try again")
    }

    private fun saveUser(createdUser: FirebaseUser?, avtUrl: String) {
        user.id = createdUser!!.uid
        user.vital = "customer"
        user.location = location
        user.avatar = avtUrl
        val userReference =
            FirebaseDatabase.getInstance("https://ezi-market-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("/users/${user.id}").push()
        userReference.setValue(user).addOnCompleteListener {
            isSuccess.value = "Tạo tài khoản thành công"
        }.addOnFailureListener {
            isSuccess.value = "Tạo tài khoản thất bại"
            FirebaseAuth.getInstance().currentUser!!.delete().addOnFailureListener {
                isSuccess.value = "Lỗi xóa tài khoản"
            }.addOnSuccessListener {
                isSuccess.value = "Xóa tài khoản thành công"
            }
        }
    }

    private suspend fun upAvatarFile(createdUser: FirebaseUser): String {
        val reference =
            FirebaseStorage.getInstance().reference.child("images/${createdUser.uid}/${System.currentTimeMillis()}.png")
        try {
            var avtUrl = ""
            val uploadTask: UploadTask.TaskSnapshot? = reference.putFile(avatarUri.value!!).await()
            if (uploadTask != null) {
                isSuccess.value = "Lưu ảnh đại diện thành công"
                try {
                    withContext(Dispatchers.Main + job) {
                        val taskUrl: Uri? = uploadTask.metadata?.reference?.downloadUrl?.await()
                        avtUrl = taskUrl!!.toString()
                    }
                    return avtUrl
                } catch (e: Exception) {
                    e.stackTrace
                }
            }
        } catch (e: Exception) {
            e.stackTrace
            isSuccess.value =
                "Thông tin cá nhân chưa được lưu: Upload ảnh đại diện không thành công: ${e.message}"
        }
        throw Exception("Can not update avatar, try again")
    }

    fun shareVerificationCode(phoneNumber: String) {
        val auth = FirebaseAuth.getInstance()
        auth.setLanguageCode("vi")
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity!!) // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun getProvinceList() {
        backGroundScope.launch {
            try {
                val getProvinces = CallLocationApi.retrofitService.getProvinces()
                val result = getProvinces.await().result
                if (result.isNotEmpty()) {
                    CoroutineScope(Dispatchers.Main + job).launch {
                        provinceList.value!!.addAll(result)
                        provinceList.notifyObserverInUI()
                    }
                }
            } catch (e: Exception) {
                Log.e("getProvinceList", e.message.toString())
            }
        }
    }

    fun setProvince(province: Province) {
        selectedProvince.value = province
        districtList.value!!.removeAll(districtList.value!!.toSet())
        wardList.value!!.removeAll(wardList.value!!.toSet())
        districtList.value!!.add(District("-1", "Chọn Quận/ Huyện"))
        wardList.value!!.add(Ward("-1", "Chọn Xã/ Phường"))
    }

    fun getDistrictList() {
        backGroundScope.launch {
            try {
                if (selectedProvince.value?.Id ?: -1 != -1) {
                    val getDistricts =
                        CallLocationApi.retrofitService.getDistrict(selectedProvince.value!!.Id)
                    val result = getDistricts.await().result
                    if (result.isNotEmpty()) {
                        CoroutineScope(Dispatchers.Main + job).launch {
                            districtList.value!!.addAll(result)
                            districtList.notifyObserverInUI()
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("getDistrictList", e.message.toString())
            }
        }
    }

    fun setDistrict(district: District) {
        selectedDistrict.value = district
        wardList.value!!.removeAll(wardList.value!!.toSet())
        wardList.value!!.add(Ward("-1", "Chọn Xã/ Phường"))
    }

    fun getWardList() {
        backGroundScope.launch {
            try {
                if (selectedDistrict.value?.Id ?: -1 != -1) {
                    val getWards =
                        CallLocationApi.retrofitService.getWards(selectedDistrict.value!!.Id)
                    val result = getWards.await().result
                    if (result.isNotEmpty()) {
                        CoroutineScope(Dispatchers.Main + job).launch {
                            wardList.value!!.addAll(result)
                            wardList.notifyObserverInUI()
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("getDistrictList", e.message.toString())
            }
        }
    }

    fun setWard(ward: Ward) {
        selectedWard.value = ward
    }

    fun initialLocation() {
        provinceList.value!!.add(Province("-1", "Chọn Tỉnh/ Thành phố", "Thành phố trung ướng"))
        districtList.value!!.add(District("-1", "Chọn Quận/ Huyện"))
        wardList.value!!.add(Ward("-1", "Chọn Xã/ Phường"))
        getProvinceList()
    }
}