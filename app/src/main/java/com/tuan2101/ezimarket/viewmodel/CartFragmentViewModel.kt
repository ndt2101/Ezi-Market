package com.tuan2101.ezimarket.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuan2101.ezimarket.dataclasses.Location
import com.tuan2101.ezimarket.dataclasses.ProductInCart
import com.tuan2101.ezimarket.dataclasses.ProductViaShopInCart

/**
 * Created by ndt2101 on 11/5/2021.
 */
class CartFragmentViewModel() : ViewModel() {

    var listProductInCart = MutableLiveData<ArrayList<MutableLiveData<ProductViaShopInCart>>>()
    val currentVisitedShopId = MutableLiveData<String>()
    var currentSelectedShopIdToGetVoucher = MutableLiveData<String>()

    init {
        listProductInCart.value = dummyDataForCart()
    }

    fun clickAllProductViaShop(shopId: String) {
        // binary search
    }

    fun clickVisitShop(shopId: String) {
        currentVisitedShopId.value = shopId
        listProductInCart.value!![0].value!!.shopName = "test click $shopId"
        Log.i("visit", listProductInCart.value?.get(0)!!.value!!.shopName)
    }

    fun clickSelectVoucher(shopId: String) {
        currentSelectedShopIdToGetVoucher.value = shopId
    }
    fun dummyDataForCart() : ArrayList<MutableLiveData<ProductViaShopInCart>> {
        val list = ArrayList<MutableLiveData<ProductViaShopInCart>>()
        val listProduct = MutableLiveData<List<MutableLiveData<ProductInCart>>>()
        val listProductInCart = ArrayList<MutableLiveData<ProductInCart>>()
        val productInCart = MutableLiveData<ProductInCart>()
        productInCart.value = ProductInCart(
            "p0",
            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/248384203_1067157620782867_6617827598330014721_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=VavUaKnuwFYAX9aToDm&_nc_ht=scontent.fhph1-1.fna&oh=92e07c5c07854ba1c60973f97c5e0ae3&oe=61A530BC",
            "Thùy Dương product",
            25000,
            25000,
            5.0f,
            Location("Doi 6 An Doai", "An binh", "Nam Sach", "Hai Duong"),2,
            true
        )
        listProductInCart.add(productInCart)

        listProduct.value = listProductInCart

        val item = ProductViaShopInCart("1",
            "Dummy Shop",
            true,
            listProduct
        )
        val mutableLiveDataItem = MutableLiveData<ProductViaShopInCart>()
        mutableLiveDataItem.value = item

        val item2 = ProductViaShopInCart("2",
            "Dummy Shop",
            true,
            listProduct
        )
        val mutableLiveDataItem2 = MutableLiveData<ProductViaShopInCart>()
        mutableLiveDataItem2.value = item2

        list.add(mutableLiveDataItem)
        list.add(mutableLiveDataItem2)
        return list
    }
}