package com.tuan2101.ezimarket.dataclasses

/**
 * Created by ndt2101 on 10/31/2021.
 */
class Shop(id: String,
           avatar: String,
           shopName: String,
           override var vital: String = "shop",
           followerList: List<User>?,
           followingList: List<User>?,
           location: Location,
           val paypalClientId: String
) : User(id, shopName, avatar, vital, followerList, followingList, location)
