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


    constructor(
        id: String,
        imageProduct: String,
        nameProduct: String,
        oldPrice: Long,
        newPrice: Long,
    ) : this() {
        this.id = id
        this.imageProduct = imageProduct
        this.nameProduct = nameProduct
        this.oldPrice = oldPrice
        this.newPrice = newPrice
    }


    override fun equals(other: Any?): Boolean {
        return other is Product
                && id == other.id
                && imageProduct == other.imageProduct
                && nameProduct == other.nameProduct
                && oldPrice == other.oldPrice
                && newPrice == other.newPrice
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + imageProduct.hashCode()
        result = 31 * result + nameProduct.hashCode()
        result = 31 * result + oldPrice.hashCode()
        result = 31 * result + newPrice.hashCode()
        return result
    }
}

class ProductInCart(
    id: String,
    imageProduct: String,
    nameProduct: String,
    oldPrice: Long,
    newPrice: Long,
    productQuantity: Long,
    status: Boolean
) : Product(id, imageProduct, nameProduct, oldPrice, newPrice) {
    @get:Bindable
    var productStatus: Boolean = status
        set(value) {
            field = value
            notifyPropertyChanged(BR.productStatus)
        }

    @get:Bindable
    var productQuantity: Long = productQuantity
        set(value) {
            field = value
            notifyPropertyChanged(BR.productQuantity)
        }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
                && other is ProductInCart
                && productStatus == other.productStatus
                && productQuantity == other.productQuantity
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + productStatus.hashCode()
        result = 31 * result + productQuantity.hashCode()
        return result
    }
}

open class SearchedProduct(
    id: String,
    imageProduct: String,
    nameProduct: String,
    oldPrice: Long,
    newPrice: Long,
    val rate: Float,
    var productQuantity: Long,
    val shopId: String,
    val location: String,
) : Product(id, imageProduct, nameProduct, oldPrice, newPrice) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other) &&
                other is SearchedProduct
                && rate == other.rate
                && productQuantity == other.productQuantity
                && shopId == other.shopId
                && location == other.location
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + rate.hashCode()
        result = 31 * result + shopId.hashCode()
        result = 31 * result + productQuantity.hashCode()
        result = 31 * result + location.hashCode()
        return result
    }
}

class ProductInShop(
    id: String,
    imageProduct: String,
    nameProduct: String,
    oldPrice: Long,
    newPrice: Long,
    rate: Float,
    productQuantity: Long,
    shopId: String,
    location: String,
    val imgList: List<String>,
    val productDescription: String,
    val leftProductAmount: Long
): SearchedProduct(id, imageProduct, nameProduct, oldPrice, newPrice, rate, productQuantity, shopId, location) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other) &&
                other is ProductInShop
                && imgList == other.imgList
                && productDescription == other.productDescription
                && leftProductAmount == other.leftProductAmount
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + imgList.hashCode()
        result = 31 * result + productDescription.hashCode()
        result = 31 * result + leftProductAmount.hashCode()
        return result
    }
}