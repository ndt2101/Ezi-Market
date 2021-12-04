package com.tuan2101.ezimarket.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeFragmentViewModel() : ViewModel() {

    val suggestedClickedItem = MutableLiveData<String>()
    val navToNotificationFragment = MutableLiveData(false)

    init {
        suggestedClickedItem.value = "0"
        Log.i("ttt", "init")
    }

    fun onClickSuggestedClickedItem(id: String) {
        suggestedClickedItem.value = id
        Log.i("ttt", id)
    }

    fun onClickNotification() {
        navToNotificationFragment.value = true
    }
}