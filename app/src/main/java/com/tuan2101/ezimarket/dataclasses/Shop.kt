package com.tuan2101.ezimarket.dataclasses

/**
 * Created by ndt2101 on 10/31/2021.
 */
class Shop(id: String,
           avatar: String,
           shopName: String,
           override var vital: String = "shop",
           var followerList: List<String>?,
           followingList: List<String>?,
           location: Location,
) : User(id, shopName, avatar, vital, followingList, location)

class PostUser(
    val id: String,
    val avatar: String,
    val name: String,
    var vital: String = "Shop"
)