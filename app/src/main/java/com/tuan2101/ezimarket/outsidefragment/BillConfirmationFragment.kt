package com.tuan2101.ezimarket.outsidefragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.adapter.ConfirmationAdapter
import com.tuan2101.ezimarket.databinding.FragmentBillConfirmationBinding
import com.tuan2101.ezimarket.dataclasses.Bill
import com.tuan2101.ezimarket.viewmodel.CartFragmentViewModel

class BillConfirmationFragment : Fragment() {

    lateinit var binding: FragmentBillConfirmationBinding
    val shareViewModel: CartFragmentViewModel by activityViewModels()
    var activityResultLauncherList = ArrayList<ActivityResultLauncher<Intent>>()
    lateinit var layoutManager: LinearLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBillConfirmationBinding.inflate(inflater, container, false)

        binding.viewModel = shareViewModel
        layoutManager =LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.listProductViaShop.layoutManager = layoutManager
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
                for (index in shareViewModel.listBills.indices) {
                    if (shareViewModel.listBills[index].shippingMethod == null) {
                        binding.scrollView.scrollTo(0, binding.listProductViaShop.getChildAt(index).y.toInt())
                        Toast.makeText(context, "Chưa chọn phương thức giao hàng $index", Toast.LENGTH_SHORT).show()
                        shareViewModel.navToPaymentDetailFragment.value = false
                        return@observe
                    }
                }
                if (shareViewModel.paymentMethod.value == "") {
                    Toast.makeText(context, "Chưa chọn hình thức thanh toán", Toast.LENGTH_SHORT).show()
                    binding.scrollView.scrollTo(0, binding.listProductViaShop.getChildAt(shareViewModel.listBills.size - 1).y.toInt() + 350)
                    shareViewModel.navToPaymentDetailFragment.value = false
                    return@observe
                }
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
}