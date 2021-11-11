package com.tuan2101.ezimarket.utils

import androidx.lifecycle.MutableLiveData

/**
 * Created by ndt2101 on 11/10/2021.
 */

fun <T> MutableLiveData<T>.notifyObserverInUI() {
    this.value = this.value
}

fun <T> MutableLiveData<T>.notifyObserverInBg() {
    this.postValue(this.value)
}