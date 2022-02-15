package com.tuan2101.ezimarket.dataclasses

/**
 * Created by ndt2101 on 10/31/2021.
 */
open class User() {
    open var id: String? = null
    open var name: String? = null
    open var avatar: String? = null
    open var vital: String? = null
    open var followingList: List<String>? = null
    open var followerList: List<String>? = null
    open var location: Location? = null

    constructor(id: String?, name: String?, avatar: String, vital: String?, followingList: List<String>?, followerList: List<String>?, location: Location?) : this() {
        this.id = id
        this.name = name
        this.avatar = avatar
        this.vital = vital
        this.followerList = followerList
        this.followingList = followingList
        this.location = location
    }
}