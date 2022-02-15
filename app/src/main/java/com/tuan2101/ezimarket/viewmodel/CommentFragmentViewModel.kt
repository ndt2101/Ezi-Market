package com.tuan2101.ezimarket.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuan2101.ezimarket.dataclasses.Comment
import com.tuan2101.ezimarket.dataclasses.Post
import com.tuan2101.ezimarket.dataclasses.DisplayUser

/**
 * Created by ndt2101 on 11/1/2021.
 */
class CommentFragmentViewModel(val post: Post?) : ViewModel() {
    var commentList: List<Comment> = ArrayList()
    var selectedUserId = MutableLiveData("")

    init {
        commentList = dummyData()
    }

    fun onNavToPersonalPage(userId: String) {
        selectedUserId.value = userId
    }

    fun dummyData() : ArrayList<Comment> {
        val list = ArrayList<Comment>()
        list.addAll(mutableListOf(
            Comment("c1",
                DisplayUser("u1",
                    "https://i.pinimg.com/564x/26/ab/79/26ab7951865d85e9077ef173aac36583.jpg",
                    "Tuan",
                    "shop",
                    true
                ),
                System.currentTimeMillis(),
                "Tesseract is an optical character recognition engine for various operating systems.[3] It is free software, released under the Apache License.[1][4][5] Originally developed by Hewlett-Packard as proprietary software in the 1980s, it was released as open source in 2005 and development has been sponsored by Google since 2006.[6]"
            ),
            Comment("c2",
                DisplayUser("u1",
                    "https://i.pinimg.com/564x/26/ab/79/26ab7951865d85e9077ef173aac36583.jpg",                    "Tuan",
                    "shop",
                    true
                ),
                System.currentTimeMillis(),
                "Tesseract is an optical character recognition"
            ),
        ))
        return list
    }
}