package com.tuan2101.ezimarket.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tuan2101.ezimarket.dataclasses.Location
import com.tuan2101.ezimarket.dataclasses.ProductViaShopInCart

/**
 * Created by ndt2101 on 11/27/2021.
 */
class BillConfirmationViewModelFactory(val location: Location, val listProductViaShop: List<ProductViaShopInCart>, var totalPrice: Long): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BillConfirmationViewModel::class.java)) {
            return BillConfirmationViewModel(location, listProductViaShop, totalPrice) as T
        }
        throw Exception("err of creating BillConfirmationViewModel")
    }
}