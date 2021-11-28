package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.databinding.FragmentBillConfirmationBinding
import com.tuan2101.ezimarket.dataclasses.ProductViaShopInCart
import com.tuan2101.ezimarket.viewmodel.BillConfirmationViewModel
import com.tuan2101.ezimarket.viewmodel.BillConfirmationViewModelFactory

class BillConfirmationFragment : Fragment() {

    lateinit var binding: FragmentBillConfirmationBinding
    lateinit var viewModel: BillConfirmationViewModel
    lateinit var factory: BillConfirmationViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBillConfirmationBinding.inflate(inflater, container, false)
        val args = BillConfirmationFragmentArgs.fromBundle(requireArguments())
        val newList: ArrayList<ProductViaShopInCart> = ArrayList(args.listProductViaShop.toList())

        factory = BillConfirmationViewModelFactory(args.loaction, newList, args.totalPrice)
        viewModel = ViewModelProvider(this, factory)[BillConfirmationViewModel::class.java]
        binding.viewModel = viewModel
        binding.listProductViaShop.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        return binding.root
    }
}