package com.tuan2101.ezimarket.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

/**
 * Created by ndt2101 on 2/12/2022.
 */
class LoginAndSigupViewModelFactory(val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginAndSignupViewModel::class.java)) {
            return LoginAndSignupViewModel(application) as T
        }
        throw IllegalArgumentException("unknow viewModel class")
    }
}