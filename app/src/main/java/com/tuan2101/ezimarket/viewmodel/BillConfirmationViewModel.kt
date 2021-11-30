package com.tuan2101.ezimarket.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.tuan2101.ezimarket.dataclasses.Bill
import com.tuan2101.ezimarket.dataclasses.Location
import com.tuan2101.ezimarket.dataclasses.ProductViaShopInCart

/**
 * Created by ndt2101 on 11/27/2021.
 */
class BillConfirmationViewModel(val location: Location,var listBills: List<Bill>, var totalPrice: Long): ViewModel() {

//    companion object {
//        var listBills: List<Bill> = ArrayList()
//    }

    init {
//        Log.i("khoi tao lai", "khoi tao lai")
    }

}