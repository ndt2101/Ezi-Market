package com.tuan2101.ezimarket.utils

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.resumeWithException
import kotlin.math.E

/**
 * Created by ndt2101 on 11/10/2021.
 */

fun <T> MutableLiveData<T>.notifyObserverInUI() {
    this.value = this.value
}

fun <T> MutableLiveData<T>.notifyObserverInBg() {
    this.postValue(this.value)
}

fun toast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@OptIn(ExperimentalCoroutinesApi::class)
public suspend fun <T> Task<T>.await(): T? {
    if (isComplete) {
        val e = exception
        return if (e == null) {
            if (isCanceled) {
                throw CancellationException(
                    "Task $this was cancelled normally.")
            } else {
                result
            }
        } else {
            throw e
        }
    }

    return suspendCancellableCoroutine { cont ->
        addOnCompleteListener {
            val e = exception
            if (e == null) {
                if (isCanceled) cont.cancel() else cont.resume(result) {
                    throw it
                }
            } else {
                cont.resumeWithException(e)
            }
        }
    }
}