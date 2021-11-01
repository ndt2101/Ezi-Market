package com.tuan2101.ezimarket.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tuan2101.ezimarket.dataclasses.Post

/**
 * Created by ndt2101 on 11/1/2021.
 */
class CommentFragmentViewModelFactory(val post: Post) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentFragmentViewModel::class.java)) {
            return CommentFragmentViewModel(post) as T
        }
        throw Exception("cant create CommentFragmentViewModelFactory")
    }
}