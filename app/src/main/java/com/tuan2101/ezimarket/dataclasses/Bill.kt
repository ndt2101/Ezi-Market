package com.tuan2101.ezimarket.dataclasses

/**
 * Created by ndt2101 on 11/29/2021.
 */
class Bill(val billId: String,
           val shopId: String,
           val shopName: String,
           val userId: String,
           val listProduct: List<Product>,
           var shippingMethod: ShippingMethod?,
           val noteToSeller: String,
           val totalPrice: Long,
           val paymentMethod: String,
           val shipToLocation: Location
) {
}