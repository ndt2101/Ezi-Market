package com.tuan2101.ezimarket.dataclasses

/**
 * Created by ndt2101 on 10/31/2021.
 */
data class Post(val id: String,
                val user: User,
                val timeCreated: Long,
                val postContentText: String,
                val postContentImage: String?,
                val product: Product?,
                val voucher: Voucher?,
                val likeList: MutableList<String>,
                val commentList: MutableList<Comment>
)