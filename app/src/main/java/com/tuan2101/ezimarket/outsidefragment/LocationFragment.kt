package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.adapter.LocationAdapter
import com.tuan2101.ezimarket.databinding.FragmentLocationBinding
import com.tuan2101.ezimarket.dataclasses.Bill
import com.tuan2101.ezimarket.dataclasses.District
import com.tuan2101.ezimarket.dataclasses.Province
import com.tuan2101.ezimarket.dataclasses.Ward
import com.tuan2101.ezimarket.viewmodel.CartFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationFragment() : Fragment() {

    lateinit var binding: FragmentLocationBinding
    val shareViewModel: CartFragmentViewModel by activityViewModels()
    lateinit var provinceSpinnerAdapter: LocationAdapter
    lateinit var districtSpinnerAdapter: LocationAdapter
    lateinit var wardSpinnerAdapter: LocationAdapter
    var checkProvince = 0
    var checkDistrict = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLocationBinding.inflate(inflater, container, false)

        shareViewModel.initialLocation()
        binding.viewModel = shareViewModel
        if (shareViewModel.location == null){
            setUpForProvince()
            setUpForDistrict()
            setUpForWard()
        } else {
            setUpForProvinceClone()
            setUpForDistrictClone()
            setUpForWardClone()
        }

        setUpForEditText()
        confirm()

        customedNavBack()

        return binding.root
    }

    private fun customedNavBack() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                shareViewModel.apply {
                    provinceList.value?.removeAll(provinceList.value!!.toSet())
                    districtList.value?.removeAll(districtList.value!!.toSet())
                    wardList.value?.removeAll(wardList.value!!.toSet())
                    selectedProvince = MutableLiveData<Province>(location?.province)
                    selectedDistrict = MutableLiveData<District>(location?.district)
                    selectedWard = MutableLiveData<Ward>(location?.ward)
                    currentProvincePosition = 0
                    currentDistrictPosition = 0
                    currentWardPosition = 0
                    receiverName = location?.name ?: ""
                    phoneNumber = location?.phoneNumber ?: ""
                    detailAddress = location?.detailAddress ?: ""
                }

                if (isEnabled) {
                    isEnabled = false
                    requireActivity().onBackPressed()
                }
            }

        })
    }

    fun setUpForProvince() {
        shareViewModel.provinceList.observe(viewLifecycleOwner, {
            Log.i("getProvinceList", it.size.toString())
            provinceSpinnerAdapter = LocationAdapter(requireContext(), R.layout.selected_location_item, shareViewModel.provinceList.value!!)
            binding.province.adapter = provinceSpinnerAdapter
        })

        binding.province.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                shareViewModel.setProvince(provinceSpinnerAdapter.getItem(p2) as Province)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    fun setUpForProvinceClone() {
        shareViewModel.provinceList.observe(viewLifecycleOwner, {
            if (it.size > 1) {
                provinceSpinnerAdapter =
                    LocationAdapter(requireContext(), R.layout.selected_location_item, shareViewModel.provinceList.value!!)
                binding.province.adapter = provinceSpinnerAdapter
                binding.province.setSelection(shareViewModel.currentProvincePosition)
                Log.i("provinceSpinnerAdapter", "${shareViewModel.selectedDistrict.value?.name}")
                Log.i("provinceSpinnerAdapter", "${shareViewModel.selectedWard.value?.name}")
            }
        })

        binding.province.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                shareViewModel.setProvince(provinceSpinnerAdapter.getItem(p2) as Province)
                checkProvince++
                if (checkProvince > 1) {
                    shareViewModel.currentDistrictPosition = 0
                    shareViewModel.currentWardPosition = 0
                    binding.ward.setSelection(0)
                    binding.district.setSelection(0)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    fun setUpForDistrict() {
        districtSpinnerAdapter = LocationAdapter(requireContext(), R.layout.selected_location_item, shareViewModel.districtList.value!!)
        binding.district.adapter = districtSpinnerAdapter
        shareViewModel.selectedProvince.observe(viewLifecycleOwner, { province ->
            shareViewModel.getDistrictList()
            shareViewModel.districtList.observe(viewLifecycleOwner, {
                Log.i("getProvinceList1", it.size.toString())
                districtSpinnerAdapter = LocationAdapter(requireContext(), R.layout.selected_location_item, shareViewModel.districtList.value!!)
                binding.district.adapter = districtSpinnerAdapter
            })
        })

        binding.district.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                shareViewModel.setDistrict( districtSpinnerAdapter.getItem(p2) as District)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    fun setUpForDistrictClone() {
        shareViewModel.selectedProvince.observe(viewLifecycleOwner, {
            if (shareViewModel.provinceList.value?.size ?: 1 > 1){
                CoroutineScope(Dispatchers.Main).launch {
                    shareViewModel.getDistrictListClone()
                    if (view != null){
                        shareViewModel.districtList.observe(viewLifecycleOwner, {
                            if (it.size > 1) {
                                districtSpinnerAdapter =
                                    LocationAdapter(requireContext(), R.layout.selected_location_item, it)
                                binding.district.adapter = districtSpinnerAdapter
                                binding.district.setSelection(shareViewModel.currentDistrictPosition)
                                Log.i("districtSpinnerAdapter", "${shareViewModel.selectedDistrict.value?.name}")
                                Log.i("districtSpinnerAdapter", "${shareViewModel.selectedWard.value?.name}")
                            }
                        })
                    }
                }
            }
        })

        binding.district.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                shareViewModel.setDistrict(districtSpinnerAdapter.getItem(p2) as District)
                checkDistrict++
                if (checkDistrict > 1) {
                    shareViewModel.currentWardPosition = 0
                    binding.ward.setSelection(0)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    fun setUpForWard() {
        wardSpinnerAdapter = LocationAdapter(requireContext(), R.layout.selected_location_item, shareViewModel.wardList.value!!)
        binding.ward.adapter = wardSpinnerAdapter
        shareViewModel.selectedDistrict.observe(viewLifecycleOwner, { province ->
            shareViewModel.getWardList()
            shareViewModel.wardList.observe(viewLifecycleOwner, {
                Log.i("getProvinceList2", it.size.toString())
                wardSpinnerAdapter = LocationAdapter(requireContext(), R.layout.selected_location_item, shareViewModel.wardList.value!!)
                binding.ward.adapter = wardSpinnerAdapter
            })
        })

        binding.ward.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                shareViewModel.setWard( wardSpinnerAdapter.getItem(p2) as Ward)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    fun setUpForWardClone() {
        shareViewModel.selectedDistrict.observe(viewLifecycleOwner, {
            Log.i("wardSpinnerAdapter", "${shareViewModel.selectedWard.value?.name}")
            if (shareViewModel.districtList.value?.size ?: 1 > 1){
                if (view != null){
                    CoroutineScope(Dispatchers.Main).launch {
                        shareViewModel.getWardListClone()
                        if (view != null) {
                            shareViewModel.wardList.observe(viewLifecycleOwner, {
                                if (it.size > 1) {
                                    Log.i("wardSpinnerAdapter", it.size.toString())
                                    wardSpinnerAdapter =
                                        LocationAdapter(requireContext(), R.layout.selected_location_item, it)
                                    binding.ward.adapter = wardSpinnerAdapter
                                    binding.ward.setSelection(shareViewModel.currentWardPosition)
                                }
                            })
                        }
                    }
                }
            }
        })

        binding.ward.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                shareViewModel.setWard(wardSpinnerAdapter.getItem(p2) as Ward)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    fun setUpForEditText() {
        binding.receiver.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                if (!p0.isNullOrEmpty()) {
                    shareViewModel.receiverName = p0.toString()
//                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        binding.phoneNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                if (!p0.isNullOrEmpty() && p0.length >= 9) {
                    shareViewModel.phoneNumber = p0.toString()
//                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        binding.detailAddress.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                if (!p0.isNullOrEmpty()) {
                    shareViewModel.detailAddress = p0.toString()
//                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    fun confirm() {
        shareViewModel.navToCartFragment.observe(viewLifecycleOwner, {
            if (it == 1) {
                shareViewModel.location = shareViewModel.updatedLocation.clone()
                requireActivity().onBackPressed()
                Toast.makeText(context,
                    shareViewModel.updatedLocation.ward.name +
                        shareViewModel.updatedLocation.district.name +
                        shareViewModel.updatedLocation.province.name +
                        shareViewModel.updatedLocation.name,
                    Toast.LENGTH_LONG).show()
                shareViewModel.navToCartFragment.value = 0
            } else if (it == -1) {
                Toast.makeText(context, "Thông tin chưa chính xác", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        parentFragment?.let { shareViewModel.districtList.removeObservers(it.viewLifecycleOwner) }
        parentFragment?.let { shareViewModel.wardList.removeObservers(it.viewLifecycleOwner) }
    }
}

// TODO: can update lai cho cung 1 location trong day va cart