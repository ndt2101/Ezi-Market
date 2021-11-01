package com.tuan2101.ezimarket.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuan2101.ezimarket.dataclasses.Post

/**
 * Created by ndt2101 on 11/1/2021.
 */
class NewsFeedFragmentViewModel : ViewModel() {
    private val _currentSubject: MutableLiveData<String> = MutableLiveData<String>()
    val currentSubject : LiveData<String>
        get() = _currentSubject

    init {
        _currentSubject.value = "1"
    }

    fun setCurrentSubject(subject: String) {
        _currentSubject.value = subject
    }
}