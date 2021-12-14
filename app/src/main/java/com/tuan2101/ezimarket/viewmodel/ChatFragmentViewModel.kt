package com.tuan2101.ezimarket.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuan2101.ezimarket.dataclasses.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by ndt2101 on 12/11/2021.
 */
class ChatFragmentViewModel: ViewModel() {
    val job = Job()
    val bgScope = CoroutineScope(Dispatchers.IO + job)
    val uIScope = CoroutineScope(Dispatchers.Main + job)
    val isLoading = MutableLiveData(true)
    val latestMessages = MutableLiveData<List<LatestMessage>>()
    val loadingMessages = MutableLiveData<List<DetailLatestMessage>>()

    /** solution
     * load latestMessage -> add vao queue
     * lay tu queue -> load user-> add vao array loadingMessage
     * pop latestMessage ra khoi queue
     */

    /**
     * neu message co type la product thi ap dung giong cach tren (da hinh roi)
     */
    init {
        latestMessages.value = latestMessageDummyData()
        loadingMessages.value = loadingMessageDummyData()
        bgScope.launch {
            Thread.sleep(2000)
            uIScope.launch {
                isLoading.value = false
            }
        }
    }

    fun latestMessageDummyData() : ArrayList<LatestMessage> {
        val list = ArrayList<LatestMessage>()
        val message1 = LatestMessage("0", System.currentTimeMillis(), "\uD83D\uDC4D", "text", "0", "1", "sent")
        val message2 = LatestMessage("1", System.currentTimeMillis(), "\uD83D\uDC4D", "text", "2", "2", "sent")
        val message3 = LatestMessage("2", System.currentTimeMillis(), "\uD83D\uDC4D", "text", "0", "3", "sent")
        val message4 = LatestMessage("3", System.currentTimeMillis(), "\uD83D\uDC4D", "text", "4", "4", "seen")
        val message5 = LatestMessage("4", System.currentTimeMillis(), "\uD83D\uDC4D", "text", "5", "5", "seen")
        val message6 = LatestMessage("5", System.currentTimeMillis(), "\uD83D\uDC4D", "text", "0", "6", "seen")
        list.addAll(listOf(message1, message2, message3, message4, message5, message6))
        return list
    }

    fun loadingMessageDummyData(): ArrayList<DetailLatestMessage> {
        val list = ArrayList<DetailLatestMessage>()
        val user = DisplayUser("1",
            "https://i.pinimg.com/564x/26/ab/79/26ab7951865d85e9077ef173aac36583.jpg",
            "User 1",
            "customer",
            false
        )
        latestMessages.value?.forEach {
            list.add(
                DetailLatestMessage(
                    it.id,
                    it.createdTime,
                    it.content,
                    it.contentType,
                    it.senderId,
                    user,
                    it.status
                )
            )
        }
        return list
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}

