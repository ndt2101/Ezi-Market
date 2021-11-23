package com.tuan2101.ezimarket.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tuan2101.ezimarket.dataclasses.Location

/**
 * Created by ndt2101 on 11/21/2021.
 */
class LocationFragmentViewModelFactory(val location: Location?): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationFragmentViewModel::class.java)) {
            return LocationFragmentViewModel(location) as T
        }
        throw Exception("can't create LocationFragmentViewModel")
    }
}