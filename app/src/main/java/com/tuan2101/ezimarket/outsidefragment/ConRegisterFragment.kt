package com.tuan2101.ezimarket.outsidefragment

import android.Manifest
import android.content.Context
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.adapter.LocationAdapter
import com.tuan2101.ezimarket.databinding.FragmentConRegisterBinding
import com.tuan2101.ezimarket.dataclasses.District
import com.tuan2101.ezimarket.dataclasses.Location
import com.tuan2101.ezimarket.dataclasses.Province
import com.tuan2101.ezimarket.dataclasses.Ward
import com.tuan2101.ezimarket.utils.toast
import com.tuan2101.ezimarket.viewmodel.LoginAndSignupViewModel
import com.tuan2101.ezimarket.viewmodel.LoginAndSigupViewModelFactory
import java.io.InputStream

class ConRegisterFragment : Fragment() {

    lateinit var binding: FragmentConRegisterBinding
    lateinit var provinceSpinnerAdapter: LocationAdapter
    lateinit var districtSpinnerAdapter: LocationAdapter
    lateinit var wardSpinnerAdapter: LocationAdapter
    val model: LoginAndSignupViewModel by activityViewModels {
        LoginAndSigupViewModelFactory(requireActivity().application)
    }

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            model.avatarUri.value = uri
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentConRegisterBinding.inflate(inflater, container, false)
        binding.model = model
        model.activity = requireActivity()
        model.initialLocation()
        setUpForProvince()
        setUpForDistrict()
        setUpForWard()
        selectAvatar()
        onNextClick()
        return binding.root
    }

    private fun selectAvatar() {
        model.selectAvatar.observe(viewLifecycleOwner) {
            if (it) {
                getContent.launch("image/*")
                model.selectAvatar.value = false
            }
        }

        model.avatarUri.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.userAvt.setImageBitmap(BitmapFactory.decodeStream(requireActivity().contentResolver.openInputStream(it)))
                binding.anim.visibility = View.GONE
                binding.label.visibility = View.GONE
            }
        }
    }

    private fun setUpForProvince() {
        model.provinceList.observe(viewLifecycleOwner) {
            Log.i("getProvinceList", it.size.toString())
            provinceSpinnerAdapter = LocationAdapter(
                requireContext(),
                R.layout.selected_location_item,
                model.provinceList.value!!
            )
            binding.province.adapter = provinceSpinnerAdapter
        }

        binding.province.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                model.setProvince(provinceSpinnerAdapter.getItem(p2) as Province)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    private fun setUpForDistrict() {
        districtSpinnerAdapter = LocationAdapter(requireContext(), R.layout.selected_location_item, model.districtList.value!!)
        binding.district.adapter = districtSpinnerAdapter
        model.selectedProvince.observe(viewLifecycleOwner) { province ->
            model.getDistrictList()
            model.districtList.observe(viewLifecycleOwner) {
                Log.i("getProvinceList1", it.size.toString())
                districtSpinnerAdapter = LocationAdapter(
                    requireContext(),
                    R.layout.selected_location_item,
                    model.districtList.value!!
                )
                binding.district.adapter = districtSpinnerAdapter
            }
        }

        binding.district.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                model.setDistrict( districtSpinnerAdapter.getItem(p2) as District)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    private fun setUpForWard() {
        wardSpinnerAdapter = LocationAdapter(requireContext(), R.layout.selected_location_item, model.wardList.value!!)
        binding.ward.adapter = wardSpinnerAdapter
        model.selectedDistrict.observe(viewLifecycleOwner) { province ->
            model.getWardList()
            model.wardList.observe(viewLifecycleOwner) {
                Log.i("getProvinceList2", it.size.toString())
                wardSpinnerAdapter = LocationAdapter(
                    requireContext(),
                    R.layout.selected_location_item,
                    model.wardList.value!!
                )
                binding.ward.adapter = wardSpinnerAdapter
            }
        }

        binding.ward.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                model.setWard( wardSpinnerAdapter.getItem(p2) as Ward)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    private fun onNextClick() {
        val context = requireContext()
        model.navToVerify.observe(viewLifecycleOwner) {
            if (it) {
                if (binding.detailAddress.text.toString().isEmpty()) {
                    toast(context, "Chưa nhập địa chỉ chi tiết")
                    binding.detailAddress.requestFocus()
                } else if(model.selectedProvince.value == null) {
                    toast(context, "Chưa chọn Tỉnh/ Thành phố")
                    binding.province.requestFocus()
                } else if(model.selectedDistrict.value == null) {
                    toast(context, "Chưa chọn Quận/ Huyện")
                    binding.district.requestFocus()
                } else if(model.selectedWard.value == null) {
                    toast(context, "Chưa chọn Xã/ Phường")
                    binding.ward.requestFocus()
                } else if (model.avatarUri.value == null) {
                    toast(context, "Chưa chọn ảnh đại diện")
                    binding.avt.requestFocus()
                } else {
                    model.location = Location(
                        model.user.name!!,
                        model.phoneNumber,
                        binding.detailAddress.text.toString(),
                        model.selectedWard.value!!,
                        model.selectedDistrict.value!!,
                        model.selectedProvince.value!!
                    )
                    Log.i("uri", model.avatarUri.toString())
                    findNavController().navigate(ConRegisterFragmentDirections.actionConRegisterFragmentToOTPFragment())
                    model.navToVerify.value = false
                }
            }
        }
    }
}