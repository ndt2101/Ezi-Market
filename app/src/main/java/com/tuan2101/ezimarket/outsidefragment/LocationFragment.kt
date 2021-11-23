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
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.adapter.LocationAdapter
import com.tuan2101.ezimarket.databinding.FragmentLocationBinding
import com.tuan2101.ezimarket.dataclasses.District
import com.tuan2101.ezimarket.dataclasses.Location
import com.tuan2101.ezimarket.dataclasses.Province
import com.tuan2101.ezimarket.dataclasses.Ward
import com.tuan2101.ezimarket.viewmodel.LocationFragmentViewModel
import com.tuan2101.ezimarket.viewmodel.LocationFragmentViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationFragment() : Fragment() {

    lateinit var binding: FragmentLocationBinding
    lateinit var viewModel: LocationFragmentViewModel
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
        val lastLocation: Location? = LocationFragmentArgs.fromBundle(requireArguments()).location
        Log.i("loccation", lastLocation.toString())
        val factory = LocationFragmentViewModelFactory(lastLocation)
        viewModel = ViewModelProvider(this, factory)[LocationFragmentViewModel::class.java]
        binding.viewModel = viewModel
        if (lastLocation == null){
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
        return binding.root
    }

    fun setUpForProvince() {
        viewModel.provinceList.observe(viewLifecycleOwner, {
            Log.i("getProvinceList", it.size.toString())
            provinceSpinnerAdapter = LocationAdapter(requireContext(), R.layout.selected_location_item, viewModel.provinceList.value!!)
            binding.province.adapter = provinceSpinnerAdapter
        })

        binding.province.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.setProvince(provinceSpinnerAdapter.getItem(p2) as Province)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    fun setUpForProvinceClone() {
        viewModel.provinceList.observe(viewLifecycleOwner, {
            if (it.size > 1) {
                provinceSpinnerAdapter =
                    LocationAdapter(requireContext(), R.layout.selected_location_item, viewModel.provinceList.value!!)
                binding.province.adapter = provinceSpinnerAdapter
                binding.province.setSelection(viewModel.currentProvincePosition)
                Log.i("provinceSpinnerAdapter", "${viewModel.selectedDistrict.value?.name}")
                Log.i("provinceSpinnerAdapter", "${viewModel.selectedWard.value?.name}")
            }
        })

        binding.province.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.setProvince(provinceSpinnerAdapter.getItem(p2) as Province)
                checkProvince++
                if (checkProvince > 1) {
                    viewModel.currentDistrictPosition = 0
                    viewModel.currentWardPosition = 0
                    binding.ward.setSelection(0)
                    binding.district.setSelection(0)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    fun setUpForDistrict() {
        districtSpinnerAdapter = LocationAdapter(requireContext(), R.layout.selected_location_item, viewModel.districtList.value!!)
        binding.district.adapter = districtSpinnerAdapter
        viewModel.selectedProvince.observe(viewLifecycleOwner, { province ->
            viewModel.getDistrictList()
            viewModel.districtList.observe(viewLifecycleOwner, {
                Log.i("getProvinceList1", it.size.toString())
                districtSpinnerAdapter = LocationAdapter(requireContext(), R.layout.selected_location_item, viewModel.districtList.value!!)
                binding.district.adapter = districtSpinnerAdapter
            })
        })

        binding.district.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.setDistrict( districtSpinnerAdapter.getItem(p2) as District)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    fun setUpForDistrictClone() {
        viewModel.selectedProvince.observe(viewLifecycleOwner, {
            if (viewModel.provinceList.value?.size ?: 1 > 1){
                CoroutineScope(Dispatchers.Main).launch {
                    viewModel.getDistrictListClone()
                    viewModel.districtList.observe(viewLifecycleOwner, {
                        if (it.size > 1) {
                            districtSpinnerAdapter = LocationAdapter(requireContext(), R.layout.selected_location_item, it)
                            binding.district.adapter = districtSpinnerAdapter
                            binding.district.setSelection(viewModel.currentDistrictPosition)
                            Log.i("districtSpinnerAdapter", "${viewModel.selectedDistrict.value?.name}")
                            Log.i("districtSpinnerAdapter", "${viewModel.selectedWard.value?.name}")
                        }
                    })
                }
            }
        })

        binding.district.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.setDistrict(districtSpinnerAdapter.getItem(p2) as District)
                checkDistrict++
                if (checkDistrict > 1) {
                    viewModel.currentWardPosition = 0
                    binding.ward.setSelection(0)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    fun setUpForWard() {
        wardSpinnerAdapter = LocationAdapter(requireContext(), R.layout.selected_location_item, viewModel.wardList.value!!)
        binding.ward.adapter = wardSpinnerAdapter
        viewModel.selectedDistrict.observe(viewLifecycleOwner, { province ->
            viewModel.getWardList()
            viewModel.wardList.observe(viewLifecycleOwner, {
                Log.i("getProvinceList2", it.size.toString())
                wardSpinnerAdapter = LocationAdapter(requireContext(), R.layout.selected_location_item, viewModel.wardList.value!!)
                binding.ward.adapter = wardSpinnerAdapter
            })
        })

        binding.ward.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.setWard( wardSpinnerAdapter.getItem(p2) as Ward)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    fun setUpForWardClone() {
        viewModel.selectedDistrict.observe(viewLifecycleOwner, {
            Log.i("wardSpinnerAdapter", "${viewModel.selectedWard.value?.name}")
            if (viewModel.districtList.value?.size ?: 1 > 1){
                CoroutineScope(Dispatchers.Main).launch {
                    viewModel.getWardListClone()
                    viewModel.wardList.observe(viewLifecycleOwner, {
                        if (it.size > 1) {
                            Log.i("wardSpinnerAdapter", it.size.toString())
                            wardSpinnerAdapter = LocationAdapter(requireContext(), R.layout.selected_location_item, it)
                            binding.ward.adapter = wardSpinnerAdapter
                            binding.ward.setSelection(viewModel.currentWardPosition)
                        }
                    })
                }
            }
        })

        binding.ward.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.setWard(wardSpinnerAdapter.getItem(p2) as Ward)
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
                    viewModel.receiverName = p0.toString()
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
                    viewModel.phoneNumber = p0.toString()
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
                    viewModel.detailAddress = p0.toString()
//                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    fun confirm() {
        viewModel.navToCartFragment.observe(viewLifecycleOwner, {
            if (it == 1) {
                findNavController().navigate(LocationFragmentDirections.actionLocationFragmentToCartFragment())
                Toast.makeText(context,
                    viewModel.updatedLocation.ward.name +
                        viewModel.updatedLocation.district.name +
                        viewModel.updatedLocation.province.name +
                        viewModel.updatedLocation.name,
                    Toast.LENGTH_LONG).show()
            } else if (it == -1) {
                Toast.makeText(context, "Thông tin chưa chính xác", Toast.LENGTH_SHORT).show()
            }
        })
    }
}