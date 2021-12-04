package com.tuan2101.ezimarket.viewmodel

import androidx.lifecycle.ViewModel
import com.tuan2101.ezimarket.dataclasses.Comment
import com.tuan2101.ezimarket.dataclasses.Post
import com.tuan2101.ezimarket.dataclasses.PostUser

/**
 * Created by ndt2101 on 11/1/2021.
 */
class CommentFragmentViewModel(val post: Post?) : ViewModel() {
    var commentList: List<Comment> = ArrayList()
    init {
        commentList = dummyData()
    }

    fun dummyData() : ArrayList<Comment> {
        val list = ArrayList<Comment>()
        list.addAll(mutableListOf(
            Comment("c1",
                PostUser("u1",
                    "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                    "Tuan",
                    "User"
                ),
                System.currentTimeMillis(),
                "Tesseract is an optical character recognition engine for various operating systems.[3] It is free software, released under the Apache License.[1][4][5] Originally developed by Hewlett-Packard as proprietary software in the 1980s, it was released as open source in 2005 and development has been sponsored by Google since 2006.[6]"
            ),
            Comment("c2",
                PostUser("u1",
                    "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                    "Tuan",
                    "User"
                ),
                System.currentTimeMillis(),
                "Tesseract is an optical character recognition"
            ),
        ))
        return list
    }
}