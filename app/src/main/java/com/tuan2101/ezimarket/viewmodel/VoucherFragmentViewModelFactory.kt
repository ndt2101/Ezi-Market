package com.tuan2101.ezimarket.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.DatabaseReference
import com.tuan2101.ezimarket.dataclasses.ProductViaShopInCart
import java.lang.Exception

/**
 * Created by ndt2101 on 11/15/2021.
 */
class VoucherFragmentViewModelFactory(val shop: ProductViaShopInCart): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VoucherFragmentViewModel::class.java)) {
            return VoucherFragmentViewModel(shop) as T
        }
        throw Exception("can't create VoucherFragmentViewModel")
    }
}