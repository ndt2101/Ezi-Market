package com.tuan2101.ezimarket.dataclasses

/**
 * Created by ndt2101 on 10/31/2021.
 */
open class User(open val id: String,
                open val name: String,
                open val avatar: String,
                open var vital: String = "customer",
                open val followerList: List<User>?,
                open val followingList: List<User>?
)
