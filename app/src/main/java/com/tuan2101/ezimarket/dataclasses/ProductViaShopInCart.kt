package com.tuan2101.ezimarket.dataclasses

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.tuan2101.ezimarket.BR

/**
 * Created by ndt2101 on 11/4/2021.
 */
class ProductViaShopInCart() : BaseObservable() {

    constructor(
        shopId: String,
        shopName: String,
        status: Boolean,
        listProduct: MutableLiveData<ArrayList<ProductInCart>>?
    ) : this() {
        this.shopId = shopId
        this.shopName = shopName
        this.status = status
        this.listProduct = listProduct
    }

    @get:Bindable
    var shopId: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.shopId)
        }

    @get:Bindable
    var shopName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.shopName)
        }

    @get:Bindable
    var status: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.status)
        }

    @get:Bindable
    var listProduct: MutableLiveData<ArrayList<ProductInCart>>? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.listProduct)
        }

    override fun equals(other: Any?): Boolean {
        return other is ProductViaShopInCart &&
                shopId == other.shopId &&
                shopName == other.shopName &&
                listProduct == other.listProduct
    }

    override fun hashCode(): Int {
        var result = shopId.hashCode()
        result = 31 * result + shopName.hashCode()
        result = 31 * result + listProduct.hashCode()
        return result
    }

}