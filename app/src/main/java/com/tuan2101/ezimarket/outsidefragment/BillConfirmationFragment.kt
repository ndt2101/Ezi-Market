package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuan2101.ezimarket.activities.MainActivity
import com.tuan2101.ezimarket.adapter.ConfirmationAdapter
import com.tuan2101.ezimarket.databinding.FragmentBillConfirmationBinding
import com.tuan2101.ezimarket.dataclasses.Bill
import com.tuan2101.ezimarket.viewmodel.BillConfirmationViewModel
import com.tuan2101.ezimarket.viewmodel.BillConfirmationViewModelFactory
import com.tuan2101.ezimarket.viewmodel.CartFragmentViewModel

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
        val bills: ArrayList<Bill> = ArrayList()

        CartFragmentViewModel.needUpdatingList.forEach { productInCartViaShop ->
            val bill = Bill(
                System.currentTimeMillis().toString(),
                productInCartViaShop.shopId,
                productInCartViaShop.shopName,
                MainActivity.userId,
                productInCartViaShop.listProduct!!,
                null,
                "",
                productInCartViaShop.newTotalPrice,
                "",
                args.loaction
            )

            bills.add(bill)
        }

        factory = BillConfirmationViewModelFactory(args.loaction, bills, args.totalPrice)
        viewModel = ViewModelProvider(requireActivity(), factory)[BillConfirmationViewModel::class.java]
        binding.viewModel = viewModel
        binding.listProductViaShop.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val confirmationAdapter = ConfirmationAdapter(
            viewModel.listBills as ArrayList,
            viewLifecycleOwner,
            ConfirmationAdapter.BillClickListener { bill ->
                setShippingMethod(bill)
            }
        )
        binding.listProductViaShop.adapter = confirmationAdapter

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                CartFragmentViewModel.needUpdatingList.removeAll(CartFragmentViewModel.needUpdatingList.toSet())

                if (isEnabled) {
                    isEnabled = false
                    requireActivity().onBackPressed()
                }
            }

        })
        return binding.root
    }

    fun setShippingMethod(bill: Bill) {
        val index = viewModel.listBills.indexOf(bill)
        if (index != -1) {
            findNavController().navigate(
                BillConfirmationFragmentDirections.actionBillConfirmationFragmentToShippingMethodFragment(
                    index,
                    bill.shopId
                )
            )
        }
    }

}