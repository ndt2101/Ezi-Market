package com.tuan2101.ezimarket.dataclasses

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.tuan2101.ezimarket.BR

open class Product() : BaseObservable() {
    @get:Bindable
    var id: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.id)
        }

    @get:Bindable
    var imageProduct: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.imageProduct)
        }

    @get:Bindable
    var nameProduct: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.nameProduct)
        }

    @get:Bindable
    var oldPrice: Long = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.oldPrice)
        }

    @get:Bindable
    var newPrice: Long = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.newPrice)
        }

    @get:Bindable
    var rate: Float = 0F
        set(value) {
            field = value
            notifyPropertyChanged(BR.rate)
        }

    @get:Bindable
    var productQuantity: Long = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.productQuantity)
        }

    @get:Bindable
    var shop: Shop? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.shop)
        }

    constructor(
        id: String,
        imageProduct: String,
        nameProduct: String,
        oldPrice: Long,
        newPrice: Long,
        rate: Float,
        productQuantity: Long,
        shop: Shop
    ) : this() {
        this.id = id
        this.imageProduct = imageProduct
        this.nameProduct = nameProduct
        this.oldPrice = oldPrice
        this.newPrice = newPrice
        this.rate = rate
        this.productQuantity = productQuantity
        this.shop = shop
    }


    override fun equals(other: Any?): Boolean {
        return other is Product
                && id == other.id
                && imageProduct == other.imageProduct
                && nameProduct == other.nameProduct
                && oldPrice == other.oldPrice
                && newPrice == other.newPrice
                && rate == other.rate
                && productQuantity == other.productQuantity
                && shop == other.shop
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + imageProduct.hashCode()
        result = 31 * result + nameProduct.hashCode()
        result = 31 * result + oldPrice.hashCode()
        result = 31 * result + newPrice.hashCode()
        result = 31 * result + rate.hashCode()
        result = 31 * result + shop.hashCode()
        result = 31 * result + productQuantity.hashCode()
        return result
    }
}

class ProductInCart(
    id: String,
    imageProduct: String,
    nameProduct: String,
    oldPrice: Long,
    newPrice: Long,
    rate: Float,
    productQuantity: Long,
    shop: Shop,
    status: Boolean
) : Product(id, imageProduct, nameProduct, oldPrice, newPrice, rate, productQuantity, shop) {
    @get:Bindable
    var productStatus: Boolean = status
        set(value) {
            field = value
            notifyPropertyChanged(BR.productStatus)
        }
}