package com.tuan2101.ezimarket.dataclasses

class Product(
    val id: String,
    val imageProduct: String,
    val nameProduct: String,
    val oldPrice: Long,
    val newPrice: Long,
    val rate: Double,
    val location: String,
    val numOfSoldProduct: Long
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
                && numOfSoldProduct == other.numOfSoldProduct
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + imageProduct.hashCode()
        result = 31 * result + nameProduct.hashCode()
        result = 31 * result + oldPrice.hashCode()
        result = 31 * result + newPrice.hashCode()
        result = 31 * result + rate.hashCode()
        result = 31 * result + location.hashCode()
        result = 31 * result + numOfSoldProduct.hashCode()
        return result
    }
}