package com.tuan2101.ezimarket.dataclasses

/**
 * Created by ndt2101 on 10/31/2021.
 */
data class Post(val id: String,
                val postUser: PostUser,
                val timeCreated: Long,
                val postContentText: String,
                val postContentImage: String?,
                val product: Product?,
                val postVoucher: PostVoucher?,
                val likeList: MutableList<String>,
                val commentQuantity: Long
)