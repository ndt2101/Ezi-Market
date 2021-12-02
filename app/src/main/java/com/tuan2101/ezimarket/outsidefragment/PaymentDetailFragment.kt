package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.adapter.ConfirmationAdapter
import com.tuan2101.ezimarket.databinding.FragmentPaymentDetailBinding
import com.tuan2101.ezimarket.dataclasses.Bill
import com.tuan2101.ezimarket.viewmodel.CartFragmentViewModel

class PaymentDetailFragment : Fragment() {

    lateinit var binding: FragmentPaymentDetailBinding
    val shareViewModel: CartFragmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPaymentDetailBinding.inflate(inflater, container, false)

        binding.rcv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rcv.adapter = ConfirmationAdapter(shareViewModel.listBills as ArrayList,
            viewLifecycleOwner, ConfirmationAdapter.BillClickListener { bill ->
                fakeFun(bill)
            })
        return binding.root
    }

    fun fakeFun(bill: Bill) {

    }


}