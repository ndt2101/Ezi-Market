package com.tuan2101.ezimarket.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuan2101.ezimarket.dataclasses.District
import com.tuan2101.ezimarket.dataclasses.Location
import com.tuan2101.ezimarket.dataclasses.Province
import com.tuan2101.ezimarket.dataclasses.Ward
import com.tuan2101.ezimarket.service.CallLocationApi
import com.tuan2101.ezimarket.utils.notifyObserverInUI
import kotlinx.coroutines.*
import java.lang.Exception

/**
 * Created by ndt2101 on 11/21/2021.
 */
class LocationFragmentViewModel(val location: Location?): ViewModel() {
    private val job = Job()
    val provinceList = MutableLiveData<ArrayList<Province>>(ArrayList())
    val districtList = MutableLiveData<ArrayList<District>>(ArrayList())
    val wardList = MutableLiveData<ArrayList<Ward>>(ArrayList())
    private val backGroundScope = CoroutineScope(Dispatchers.IO + job)
    val selectedProvince = MutableLiveData<Province>(location?.province)
    val selectedDistrict = MutableLiveData<District>(location?.district)
    val selectedWard = MutableLiveData<Ward>(location?.ward)
    var currentProvincePosition = 0
    var currentDistrictPosition = 0
    var currentWardPosition = 0
    var receiverName: String = location?.name ?: ""
    var phoneNumber: String = location?.phoneNumber ?: ""
    var detailAddress: String = location?.detailAddress ?: ""
    val navToCartFragment = MutableLiveData(0)
    lateinit var updatedLocation: Location
    init {
        provinceList.value!!.add(Province("-1", "Chọn Tỉnh/ Thành phố", "Thành phố trung ướng"))
        districtList.value!!.add(District("-1", "Chọn Quận/ Huyện"))
        wardList.value!!.add(Ward("-1", "Chọn Xã/ Phường"))
        if (location == null) {
            getProvinceList()
        } else {
//            selectedProvince.value = location.province
//            selectedDistrict.value = location.district
//            selectedWard.value = location.ward
            getProvinceListClone()
//            getDistrictListClone()
//            getWardListClone()
        }
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

    fun getProvinceListClone() {
        backGroundScope.launch {
            try {
                val getProvinces = CallLocationApi.retrofitService.getProvinces()
                val result = getProvinces.await().result
                if (result.isNotEmpty()) {
                    CoroutineScope(Dispatchers.Main + job).launch {
                        provinceList.value!!.addAll(result)
                        for (index in 0 until provinceList.value!!.size) {
                            if (provinceList.value!![index].Id == selectedProvince.value!!.Id) {
                                currentProvincePosition = index
                                break
                            }
                        }
                        provinceList.notifyObserverInUI()
                        districtList.value!!.removeAll(districtList.value!!.toSet())
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
                    val getDistricts = CallLocationApi.retrofitService.getDistrict(selectedProvince.value!!.Id)
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

    suspend fun getDistrictListClone() {
        backGroundScope.launch {
            try {
                if (selectedProvince.value?.Id ?: -1 != -1) {
                    val getDistricts = CallLocationApi.retrofitService.getDistrict(selectedProvince.value!!.Id)
                    val result = getDistricts.await().result
                    if (result.isNotEmpty()) {
                        CoroutineScope(Dispatchers.Main + job).launch {
                            districtList.value!!.addAll(result)
                            for (index in 0 until districtList.value!!.size) {
                                if (districtList.value!![index].Id == selectedDistrict.value!!.Id) {
                                    currentDistrictPosition = index
                                    break
                                } else {
                                    currentDistrictPosition = 0
                                }
                            }
                            districtList.notifyObserverInUI()
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("getDistrictListClone", e.message.toString())
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
                    val getWards = CallLocationApi.retrofitService.getWards(selectedDistrict.value!!.Id)
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

    suspend fun getWardListClone() {
        backGroundScope.launch {
            try {
                if (selectedDistrict.value?.Id ?: -1 != -1) {
                    val getWards = CallLocationApi.retrofitService.getWards(selectedDistrict.value!!.Id)
                    val result = getWards.await().result
                    if (result.isNotEmpty()) {
                        CoroutineScope(Dispatchers.Main + job).launch {
                            wardList.value!!.addAll(result)
                            for (index in 0 until wardList.value!!.size) {
                                if (wardList.value!![index].Id == selectedWard.value!!.Id) {
                                    currentWardPosition = index
                                    break
                                } else {
                                    currentWardPosition = 0
                                }
                            }
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

    fun onConfirm() {
        if (receiverName == "" || phoneNumber== "" || detailAddress == ""
            || selectedProvince.value!!.Id == "-1" || selectedDistrict.value!!.Id == "-1" || selectedWard.value!!.Id == "-1") {
            navToCartFragment.value = -1
        } else {
            updatedLocation = Location(receiverName,
                phoneNumber,
                detailAddress,
                selectedWard.value!!,
                selectedDistrict.value!!,
                selectedProvince.value!!)
            navToCartFragment.value = 1

            // TODO: update location on Firebase
        }
    }
    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}