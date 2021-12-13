package com.tuan2101.ezimarket.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tuan2101.ezimarket.dataclasses.LatestMessage

/**
 * Created by ndt2101 on 12/13/2021.
 */
class ChatLogFragmentViewModelFactory(val latestMessage: LatestMessage): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ChatLogFragmentViewModel::class.java)) {
            return ChatLogFragmentViewModel(latestMessage) as T
        }
        throw Exception("can not create ChatLogFragmentViewModel")
    }
}