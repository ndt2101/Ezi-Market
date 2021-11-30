package com.tuan2101.ezimarket.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tuan2101.ezimarket.dataclasses.Bill
import java.lang.Exception

/**
 * Created by ndt2101 on 11/29/2021.
 */
class ShippingMethodFragmentViewModelFactory(val shopId: String, val listBills: List<Bill>, val position: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShippingMethodFragmentViewModel::class.java)) {
            return ShippingMethodFragmentViewModel(shopId, listBills, position) as T
        }
        throw Exception("cant create ShippingMethodFragmentViewModel object")
    }
}