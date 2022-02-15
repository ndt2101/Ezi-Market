package com.tuan2101.ezimarket.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.Exception

/**
 * Created by ndt2101 on 1/25/2022.
 */
class PersonalPageFragmentViewModelFactory(val userId: String): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonalPageFragmentViewModel::class.java)) {
            return PersonalPageFragmentViewModel(userId) as T
        }
        throw Exception("Error of create PersonalPageFragmentViewModel")
    }
}