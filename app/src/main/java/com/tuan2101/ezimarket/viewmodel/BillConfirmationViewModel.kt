package com.tuan2101.ezimarket.viewmodel

import androidx.lifecycle.ViewModel
import com.tuan2101.ezimarket.dataclasses.Location
import com.tuan2101.ezimarket.dataclasses.ProductViaShopInCart

/**
 * Created by ndt2101 on 11/27/2021.
 */
class BillConfirmationViewModel(val location: Location, val listProductViaShop: List<ProductViaShopInCart>, var totalPrice: Long): ViewModel() {

}