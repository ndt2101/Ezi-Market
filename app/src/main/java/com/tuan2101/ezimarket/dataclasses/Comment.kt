package com.tuan2101.ezimarket.dataclasses

/**
 * Created by ndt2101 on 11/1/2021.
 */
data class Comment(
    val id: String,
    val user: DisplayUser,
    val createdTime: Long,
    val content: String
)