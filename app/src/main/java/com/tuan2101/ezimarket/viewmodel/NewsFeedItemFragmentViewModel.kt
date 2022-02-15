package com.tuan2101.ezimarket.viewmodel

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuan2101.ezimarket.dataclasses.Post
import kotlinx.parcelize.Parcelize

/**
 * Created by ndt2101 on 11/1/2021.
 */
class NewsFeedItemFragmentViewModel() : ViewModel() {

    val currentPost = MutableLiveData<Post>()
    val oldPost = MutableLiveData<Post>()
    val selectedUserId = MutableLiveData("")


    fun setCurrentPost(post: Post) {
        currentPost.value = post
        oldPost.value = currentPost.value
    }

    fun onNavToPersonalPage(userId: String) {
        selectedUserId.value = userId
    }
}