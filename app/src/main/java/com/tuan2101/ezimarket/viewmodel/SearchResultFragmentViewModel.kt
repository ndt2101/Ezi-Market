package com.tuan2101.ezimarket.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by ndt2101 on 12/24/2021.
 */
class SearchResultFragmentViewModel(var _target: String) : ViewModel() {
    val target = MutableLiveData(_target)
}