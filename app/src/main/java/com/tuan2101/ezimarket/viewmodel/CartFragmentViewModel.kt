package com.tuan2101.ezimarket.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuan2101.ezimarket.dataclasses.Location
import com.tuan2101.ezimarket.dataclasses.ProductInCart
import com.tuan2101.ezimarket.dataclasses.ProductViaShopInCart
import com.tuan2101.ezimarket.dataclasses.Shop
import com.tuan2101.ezimarket.utils.notifyObserverInUI

/**
 * Created by ndt2101 on 11/5/2021.
 */
class CartFragmentViewModel() : ViewModel() {

    var listProductInCart = MutableLiveData<ArrayList<ProductViaShopInCart>>()
    val currentVisitedShopId = MutableLiveData<String>()
    var currentSelectedShopIdToGetVoucher = MutableLiveData<String>()
    var deleteShopPosition = MutableLiveData<Int>()

    init {
        listProductInCart.value = dummyDataForCart()
    }

    fun clickAllProductViaShop(shopId: String) {
        // binary search
    }

    fun clickVisitShop(shopId: String) {
        currentVisitedShopId.value = shopId
    }

    fun clickSelectVoucher(shopId: String) {
        currentSelectedShopIdToGetVoucher.value = shopId
    }

    fun clickToPay(position: Int, shopPosition: Int) {
        Log.i("ooo", position.toString() + shopPosition.toString())
    }

    fun clickToVisitProductDetail(productInCart: ProductInCart) {

    }

    fun clickToBuyMore(position: Int, shopPosition: Int) {

    }

    fun clickToBuyLess(position: Int, shopPosition: Int) {

    }

    fun clickToDeleteProduct(position: Int, shopPosition: Int) {
        Log.i("a", "shopId ${listProductInCart.value!![shopPosition].shopId} productId ${listProductInCart.value!![shopPosition].listProduct!!.value?.get(position)?.value?.id}")
        (listProductInCart.value!![shopPosition].listProduct!!.value as ArrayList).removeAt(position)
        listProductInCart.value!![shopPosition].listProduct!!.notifyObserverInUI()
        Log.i("aa", listProductInCart.value!![shopPosition].listProduct!!.value!!.size.toString())
        if (listProductInCart.value!![shopPosition].listProduct!!.value?.size == 0) {
            listProductInCart.value?.removeAt(shopPosition)
            listProductInCart.notifyObserverInUI()
            deleteShopPosition.value = shopPosition
        }

    }
    fun dummyDataForCart() : ArrayList<ProductViaShopInCart> {
        val list = ArrayList<ProductViaShopInCart>()
        val listProduct = MutableLiveData<ArrayList<MutableLiveData<ProductInCart>>>()
        val listProductInCart = ArrayList<MutableLiveData<ProductInCart>>()
        val productInCart = MutableLiveData<ProductInCart>()
        productInCart.value = ProductInCart(
            "p0",
            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/248384203_1067157620782867_6617827598330014721_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=VavUaKnuwFYAX9aToDm&_nc_ht=scontent.fhph1-1.fna&oh=92e07c5c07854ba1c60973f97c5e0ae3&oe=61A530BC",
            "Thùy Dương product",
            25000,
            25000,
            5.0f,
            2,
            Shop(
                "u1",
                "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                "Thùy Dương",
                "shop",
                null,
                null,
                Location(
                    "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                    "Mỹ Đình 2",
                    "Nam Từ Liêm",
                    "Hà Nội"
                )
            ),
            true
        )

        val productInCart2 = MutableLiveData<ProductInCart>()
        productInCart2.value = ProductInCart(
            "p1",
            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/248384203_1067157620782867_6617827598330014721_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=VavUaKnuwFYAX9aToDm&_nc_ht=scontent.fhph1-1.fna&oh=92e07c5c07854ba1c60973f97c5e0ae3&oe=61A530BC",
            "Thùy Dương product",
            25000,
            25000,
            5.0f,
            2,
            Shop(
                "u1",
                "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                "Thùy Dương",
                "shop",
                null,
                null,
                Location(
                    "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                    "Mỹ Đình 2",
                    "Nam Từ Liêm",
                    "Hà Nội"
                )
            ),
            true
        )
        val listProduct1 = MutableLiveData<ArrayList<MutableLiveData<ProductInCart>>>()
        val listProduct2 = MutableLiveData<ArrayList<MutableLiveData<ProductInCart>>>()
        val listProduct3 = MutableLiveData<ArrayList<MutableLiveData<ProductInCart>>>()
        val listProduct4 = MutableLiveData<ArrayList<MutableLiveData<ProductInCart>>>()
        val listProductInCart2 = ArrayList<MutableLiveData<ProductInCart>>()
        val listProductInCart3 = ArrayList<MutableLiveData<ProductInCart>>()
        val listProductInCart4 = ArrayList<MutableLiveData<ProductInCart>>()
        val listProductInCart5 = ArrayList<MutableLiveData<ProductInCart>>()

        listProductInCart.add(productInCart)
        listProductInCart.add(productInCart2)

        listProductInCart2.add(productInCart)
        listProductInCart2.add(productInCart2)

        listProductInCart3.add(productInCart)
        listProductInCart3.add(productInCart2)

        listProductInCart4.add(productInCart)
        listProductInCart4.add(productInCart2)

        listProductInCart5.add(productInCart)
        listProductInCart5.add(productInCart2)

        listProduct.value = listProductInCart
        listProduct1.value = listProductInCart2
        listProduct2.value = listProductInCart3

        listProduct3.value = listProductInCart4
        listProduct4.value = listProductInCart5

        val item = ProductViaShopInCart("1",
            "Shop",
            true,
            listProduct
        )

        val item2 = ProductViaShopInCart("2",
            "Dummy",
            true,
            listProduct1
        )

        val item3 = ProductViaShopInCart("3",
            "Dummy Shop",
            true,
            listProduct2
        )

        val item4 = ProductViaShopInCart("4",
            "a",
            true,
            listProduct3
        )

        val item5 = ProductViaShopInCart("5",
            "b",
            true,
            listProduct4
        )
        list.add(item)
        list.add(item2)
        list.add(item3)
        list.add(item4)
        list.add(item5)
        return list
    }
}