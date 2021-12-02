package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuan2101.ezimarket.adapter.ShippingMethodAdapter
import com.tuan2101.ezimarket.databinding.FragmentShippingMethodBinding
import com.tuan2101.ezimarket.viewmodel.CartFragmentViewModel

class ShippingMethodFragment : Fragment() {

    lateinit var binding: FragmentShippingMethodBinding
    val shareViewModel: CartFragmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShippingMethodBinding.inflate(inflater, container, false)
        shareViewModel.initialShippingMethod()
        binding.viewModel = shareViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.methodList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        shareViewModel.shippingMethodList.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                binding.methodList.adapter =
                    ShippingMethodAdapter(it, viewLifecycleOwner, ShippingMethodAdapter.ShippingMethodListener { method ->
                        shareViewModel.clickShippingMethod(method)
                    })
            }
        })

        shareViewModel.navToBillFragment.observe(viewLifecycleOwner, {
            if (it == 1) {
                requireActivity().onBackPressed()
                shareViewModel.navToBillFragment.value = 0
            } else if (it == -1) {
                Toast.makeText(context, "Bạn chưa có lựa chọn nào", Toast.LENGTH_SHORT).show()
                shareViewModel.navToBillFragment.value = 0
            }
        })
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        parentFragment?.let { shareViewModel.navToBillFragment.removeObservers(it.viewLifecycleOwner) }
    }
}