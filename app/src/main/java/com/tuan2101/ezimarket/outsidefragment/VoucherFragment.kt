package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DatabaseReference
import com.tuan2101.ezimarket.adapter.VoucherAdapter
import com.tuan2101.ezimarket.databinding.FragmentVoucherBinding
import com.tuan2101.ezimarket.dataclasses.ProductViaShopInCart
import com.tuan2101.ezimarket.dataclasses.Voucher
import com.tuan2101.ezimarket.viewmodel.*

/**
 * Created by ndt2101 on 11/14/2021.
 */
class VoucherFragment() : BottomSheetDialogFragment() {

    var shop: ProductViaShopInCart? = null
    lateinit var setVoucher: (voucher: Voucher) -> Unit
    companion object {
        val TAG: String = "VoucherFragment"
    }
    constructor(shop: ProductViaShopInCart, setVoucher: (voucher: Voucher) -> Unit): this() {
        this.shop = shop
        this.setVoucher = setVoucher
    }
    lateinit var binding: FragmentVoucherBinding
    lateinit var viewModel: VoucherFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflate layout
        binding = FragmentVoucherBinding.inflate(inflater, container, false)

        val reference: DatabaseReference? = null // từ detail shop mà ra
        val adapter = VoucherAdapter(viewLifecycleOwner, VoucherAdapter.OnClickListener { voucher -> setVoucher(voucher) })
        if (shop != null) {
            val factory = VoucherFragmentViewModelFactory(shop!!)
            viewModel = ViewModelProvider(this, factory)[VoucherFragmentViewModel::class.java]
        } else if (shop == null) {
            val factory = VoucherFragmentViewModelFactory(shop = ProductViaShopInCart("","",false,0L,0L,0,null,null))
            viewModel = ViewModelProvider(this, factory)[VoucherFragmentViewModel::class.java]
        }

        binding.title.text = "Khuyến mại của " + viewModel.shopName
        binding.rcv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter.submitList(viewModel.listVoucher)
        binding.rcv.adapter = adapter

        return binding.root
    }
}