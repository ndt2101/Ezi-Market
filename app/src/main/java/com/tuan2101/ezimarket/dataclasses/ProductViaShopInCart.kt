package com.tuan2101.ezimarket.dataclasses

import android.os.Parcel
import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.tuan2101.ezimarket.BR
import kotlinx.parcelize.Parcelize
import java.io.Serializable

/**
 * Created by ndt2101 on 11/4/2021.
 */

class ProductViaShopInCart() : BaseObservable(), Parcelable {

    constructor(
        shopId: String,
        shopName: String,
        status: Boolean,
        oldTotalPrice: Long,
        newTotalPrice: Long,
        currentSelectedProductCount: Int,
        voucher: Voucher?,
        listProduct: ArrayList<ProductInCart>?
    ) : this() {
        this.shopId = shopId
        this.shopName = shopName
        this.status = status
        this.oldTotalPrice = oldTotalPrice
        this.newTotalPrice = newTotalPrice
        this.currentSelectedProductCount = currentSelectedProductCount
        this.voucher = voucher
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
    var oldTotalPrice: Long = 0L
        set(value) {
            field = value
            notifyPropertyChanged(BR.oldTotalPrice)
        }

    @get:Bindable
    var newTotalPrice: Long = 0L
        set(value) {
            field = value
            notifyPropertyChanged(BR.newTotalPrice)
        }

    @get:Bindable
    var currentSelectedProductCount: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.currentSelectedProductCount)
        }

    @get:Bindable
    var voucher: Voucher? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.voucher)
        }


    @get:Bindable
    var listProduct: ArrayList<ProductInCart>? = ArrayList()
        set(value) {
            field = value
            notifyPropertyChanged(BR.listProduct)
        }

    constructor(parcel: Parcel) : this() {

    }

    override fun equals(other: Any?): Boolean {
        return other is ProductViaShopInCart &&
                shopId == other.shopId &&
                shopName == other.shopName &&
                listProduct == other.listProduct &&
                oldTotalPrice == other.oldTotalPrice &&
                newTotalPrice == other.newTotalPrice &&
                currentSelectedProductCount == other.currentSelectedProductCount &&
                voucher == other.voucher
    }

    override fun hashCode(): Int {
        var result = shopId.hashCode()
        result = 31 * result + shopName.hashCode()
        result = 31 * result + oldTotalPrice.hashCode()
        result = 31 * result + newTotalPrice.hashCode()
        result = 31 * result + currentSelectedProductCount.hashCode()
        result = 31 * result + voucher.hashCode()
        result = 31 * result + listProduct.hashCode()
        return result
    }

    // update lại hàm này
    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductViaShopInCart> {
        override fun createFromParcel(parcel: Parcel): ProductViaShopInCart {
            return ProductViaShopInCart(parcel)
        }

        override fun newArray(size: Int): Array<ProductViaShopInCart?> {
            return arrayOfNulls(size)
        }

    }

}