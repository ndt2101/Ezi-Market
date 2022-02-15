package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.adapter.VoucherAdapter
import com.tuan2101.ezimarket.databinding.FragmentCouponBinding
import com.tuan2101.ezimarket.viewmodel.CouponFragmentViewModel

class CouponFragment : Fragment() {


    lateinit var binding: FragmentCouponBinding
    lateinit var viewModel: CouponFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val adapter = VoucherAdapter(viewLifecycleOwner, VoucherAdapter.OnClickListener {
            voucher ->  viewModel.onClickVoucher(voucher)
        })
        // Inflate the layout for this fragment
        binding = FragmentCouponBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[CouponFragmentViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.rcv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rcv.adapter = adapter
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (!it) {
                adapter.submitList(viewModel.listVoucher)
            }
        }

        return binding.root
    }

}