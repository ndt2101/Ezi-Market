package com.tuan2101.ezimarket.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.math.E

/**
 * Created by ndt2101 on 12/24/2021.
 */
class SearchResultFragmentViewModelFactory(var target: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchResultFragmentViewModel::class.java)) {
            return SearchResultFragmentViewModel(target) as T
        }
        throw Exception("can't init SearchResultFragmentViewModel")
    }
}