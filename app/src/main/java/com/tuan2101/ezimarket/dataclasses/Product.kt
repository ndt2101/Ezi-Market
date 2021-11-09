package com.tuan2101.ezimarket.dataclasses

open class Product(
    val id: String,
    val imageProduct: String,
    val nameProduct: String,
    val oldPrice: Long,
    val newPrice: Long,
    val rate: Float,
    val location: Location,
    val productQuantity: Long,
) {
    override fun equals(other: Any?): Boolean {
        return other is Product
                && id == other.id
                && imageProduct == other.imageProduct
                && nameProduct == other.nameProduct
                && oldPrice == other.oldPrice
                && newPrice == other.newPrice
                && rate == other.rate
                && location == other.location
                && productQuantity == other.productQuantity
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + imageProduct.hashCode()
        result = 31 * result + nameProduct.hashCode()
        result = 31 * result + oldPrice.hashCode()
        result = 31 * result + newPrice.hashCode()
        result = 31 * result + rate.hashCode()
        result = 31 * result + location.hashCode()
        result = 31 * result + productQuantity.hashCode()
        return result
    }
}

class ProductInCart(id: String,
                    imageProduct: String,
                    nameProduct: String,
                    oldPrice: Long,
                    newPrice: Long,
                    rate: Float,
                    location: Location,
                    productQuantity: Long,
                    val status: Boolean
) : Product(id, imageProduct, nameProduct, oldPrice, newPrice, rate, location, productQuantity) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other) && status == (other as ProductInCart).status
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + status.hashCode()
        return result
    }
}