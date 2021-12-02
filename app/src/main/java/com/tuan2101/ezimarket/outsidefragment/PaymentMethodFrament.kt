package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.databinding.FragmentPaymentMethodBinding
import com.tuan2101.ezimarket.viewmodel.CartFragmentViewModel


class PaymentMethodFrament : Fragment() {

    lateinit var binding: FragmentPaymentMethodBinding
    val shareViewModel: CartFragmentViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPaymentMethodBinding.inflate(inflater, container, false)

        binding.viewModel = shareViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}