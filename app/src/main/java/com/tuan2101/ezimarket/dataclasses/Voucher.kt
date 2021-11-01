package com.tuan2101.ezimarket.dataclasses

import java.util.*

/**
 * Created by ndt2101 on 10/31/2021.
 */
data class Voucher(val id: String,
                   val shop: Shop,
                   val discount: Double,
                   val description: String,
                   val exp: Date,
                   val priceCondition: Long,
                   val quantity: Int
)
