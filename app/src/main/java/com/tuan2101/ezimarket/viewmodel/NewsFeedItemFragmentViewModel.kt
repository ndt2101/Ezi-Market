package com.tuan2101.ezimarket.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuan2101.ezimarket.dataclasses.Post

/**
 * Created by ndt2101 on 11/1/2021.
 */
class NewsFeedItemFragmentViewModel() : ViewModel() {

    companion object {
        val currentPost = MutableLiveData<Post>()
        val oldCurrentPost = MutableLiveData<Post>()

        fun setCurrentPost(post: Post) {
            currentPost.value = post
            oldCurrentPost.value = currentPost.value
        }
    }
}