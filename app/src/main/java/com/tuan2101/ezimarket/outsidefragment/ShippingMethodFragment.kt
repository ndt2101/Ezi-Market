package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuan2101.ezimarket.adapter.ShippingMethodAdapter
import com.tuan2101.ezimarket.databinding.FragmentShippingMethodBinding
import com.tuan2101.ezimarket.viewmodel.BillConfirmationViewModel
import com.tuan2101.ezimarket.viewmodel.ShippingMethodFragmentViewModel
import com.tuan2101.ezimarket.viewmodel.ShippingMethodFragmentViewModelFactory

class ShippingMethodFragment : Fragment() {

    lateinit var binding: FragmentShippingMethodBinding
    lateinit var billConfirmationViewModel: BillConfirmationViewModel
    lateinit var viewModel: ShippingMethodFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShippingMethodBinding.inflate(inflater, container, false)
        val position: Int = ShippingMethodFragmentArgs.fromBundle(requireArguments()).position
        val shopId: String = ShippingMethodFragmentArgs.fromBundle(requireArguments()).shopId
        val factory = ShippingMethodFragmentViewModelFactory(shopId, billConfirmationViewModel.listBills, position)
        viewModel = ViewModelProvider(this, factory)[ShippingMethodFragmentViewModel::class.java]
        binding.viewModel = viewModel

        binding.methodList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        viewModel.shippingMethodList.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                binding.methodList.adapter =
                    ShippingMethodAdapter(it, viewLifecycleOwner, ShippingMethodAdapter.ShippingMethodListener { method ->
                        viewModel.clickShippingMethod(method)
                    })
            }
        })

        viewModel.navToBillFragment.observe(viewLifecycleOwner, {
            if (it == 1) {
                requireActivity().onBackPressed()
                viewModel.navToBillFragment.value = 0
            } else if (it == -1) {
                Toast.makeText(context, "Bạn chưa có lựa chọn nào", Toast.LENGTH_SHORT).show()
            }
        })
        return binding.root
    }
}