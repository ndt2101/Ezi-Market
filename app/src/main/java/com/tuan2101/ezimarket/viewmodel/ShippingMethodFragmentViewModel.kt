package com.tuan2101.ezimarket.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuan2101.ezimarket.dataclasses.Bill
import com.tuan2101.ezimarket.dataclasses.ShippingMethod
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by ndt2101 on 11/29/2021.
 */
class ShippingMethodFragmentViewModel(val shopId: String, val listBills: List<Bill>, val position: Int): ViewModel() {
    val shippingMethodList = MutableLiveData<List<ShippingMethod>>()
    val navToBillFragment = MutableLiveData(0)
    init {
        shippingMethodList.value = dummyData()
        (shippingMethodList.value as ArrayList<ShippingMethod>).forEach{
            it.shippingMethodStatus = listBills[position].shippingMethod?.id == it.id
            Log.i("check", listBills[position].shopName)
        }
    }

    private fun dummyData(): ArrayList<ShippingMethod> {
        val list = ArrayList<ShippingMethod>()
        val method1 = ShippingMethod(System.currentTimeMillis().toString(), "Chuyen phat nhanh", 30000, Date())
        Thread.sleep(1)
        val method2 = ShippingMethod(System.currentTimeMillis().toString(), "Giao hang tiet kiem", 20000, Date())

        list.add(method1)
        list.add(method2)
        return list
    }

    fun clickShippingMethod(shippingMethod: ShippingMethod) {
        if (shippingMethod.shippingMethodStatus) {
            shippingMethod.shippingMethodStatus = false
//            BillConfirmationViewModel.listBills[position].shippingMethod = null

        } else {
            shippingMethodList.value!!.forEach {
                if (it.id != shippingMethod.id) {
                    it.shippingMethodStatus = false
                }
            }
            shippingMethod.shippingMethodStatus = true
//            BillConfirmationViewModel.listBills[position].shippingMethod = shippingMethod
        }
    }

    fun confirm() {
        for (it in shippingMethodList.value!!) {
            if (it.shippingMethodStatus) {
                listBills[position].shippingMethod = it // can xoa
                navToBillFragment.value = 1
                return
            }
        }
        navToBillFragment.value = -1
    }
}