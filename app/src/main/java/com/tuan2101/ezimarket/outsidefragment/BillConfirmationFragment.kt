package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.paypal.android.sdk.payments.PayPalConfiguration
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.adapter.ConfirmationAdapter
import com.tuan2101.ezimarket.databinding.FragmentBillConfirmationBinding
import com.tuan2101.ezimarket.dataclasses.Bill
import com.tuan2101.ezimarket.viewmodel.CartFragmentViewModel

class BillConfirmationFragment : Fragment() {

    lateinit var binding: FragmentBillConfirmationBinding
    val shareViewModel: CartFragmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentBillConfirmationBinding.inflate(inflater, container, false)

        binding.viewModel = shareViewModel
        binding.listProductViaShop.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val confirmationAdapter = ConfirmationAdapter(
            shareViewModel.listBills as ArrayList,
            viewLifecycleOwner,
            ConfirmationAdapter.BillClickListener { bill ->
                setShippingMethod(bill)
            }
        )
        binding.listProductViaShop.adapter = confirmationAdapter

        customizedNavBack()

        shareViewModel.navToPaymentMethodFragment.observe(viewLifecycleOwner, {
            if (it) {
                findNavController().navigate(R.id.action_billConfirmationFragment_to_paymentMethodFrament)
                shareViewModel.navToPaymentMethodFragment.value = false
            }
        })

        shareViewModel.navToPaymentDetailFragment.observe(viewLifecycleOwner, {
            if (it) {
                findNavController().navigate(R.id.action_billConfirmationFragment_to_paymentDetailFragment)
                shareViewModel.navToPaymentDetailFragment.value = false
            }
        })

        return binding.root
    }

    private fun customizedNavBack() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                shareViewModel.needUpdatingList.removeAll(shareViewModel.needUpdatingList.toSet())
                (shareViewModel.listBills as ArrayList<Bill>).removeAll(shareViewModel.listBills.toSet())
                shareViewModel.shippingTotalPrice = 0

                if (isEnabled) {
                    isEnabled = false
                    requireActivity().onBackPressed()
                }
            }

        })
    }

    fun setShippingMethod(bill: Bill) {
        shareViewModel.currentBill = bill
        findNavController().navigate(
            BillConfirmationFragmentDirections.actionBillConfirmationFragmentToShippingMethodFragment()
        )
    }

    fun processPayment() {
        shareViewModel.listBills.forEach {
//            val config = PayPalConfiguration()
//                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
//                .clientId(it.)
        }
    }

}