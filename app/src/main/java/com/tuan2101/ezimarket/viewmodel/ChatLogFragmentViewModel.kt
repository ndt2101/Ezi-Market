package com.tuan2101.ezimarket.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuan2101.ezimarket.dataclasses.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by ndt2101 on 12/13/2021.
 */
class ChatLogFragmentViewModel(var latestMessage: LatestMessage) : ViewModel() {
    val job = Job()
    val bgScope = CoroutineScope(Dispatchers.IO + job)
    val uIScope = CoroutineScope(Dispatchers.Main + job)
    var partner: MutableLiveData<DisplayUser> = MutableLiveData()
    var messages: MutableLiveData<List<Message>> = MutableLiveData()
    var observableLatestMessage: ObservableLatestMessage = ObservableLatestMessage(latestMessage)

    init {
        dummyPartner()
        dummyMessages()
    }

    fun dummyPartner() {
        bgScope.launch {
            Thread.sleep(2000)
            uIScope.launch {
                partner.value = DisplayUser(
                    "1",
                    "https://i.pinimg.com/564x/26/ab/79/26ab7951865d85e9077ef173aac36583.jpg",
                    "User 1",
                    "customer",
                    true
                )
            }
        }
    }

    fun dummyMessages() {
        val list = ArrayList<Message>()
        val message0 = Message(
            "0",
            System.currentTimeMillis(),
            "test",
            "text",
            "0",
            "sent"
        )

        val message1 = Message(
            "1",
            System.currentTimeMillis(),
            "https://i.pinimg.com/564x/26/ab/79/26ab7951865d85e9077ef173aac36583.jpg",
            "image",
            "1",
            "sent"
        )
        val message2 = Message(
            "2",
            System.currentTimeMillis(),
            "test text 0",
            "text",
            "0",
            "sent"
        )

        val message3 = Message(
            "3",
            System.currentTimeMillis(),
            "test text",
            "text",
            "1",
            "sent"
        )

        val message4 = Message(
            "4",
            System.currentTimeMillis(),
            "https://i.pinimg.com/564x/26/ab/79/26ab7951865d85e9077ef173aac36583.jpg",
            "image",
            "0",
            "sent"
        )

        val message5 = Message(
            "5",
            System.currentTimeMillis(),
            "last message",
            "text",
            "0",
            "sent"
        )

        val message6 = Message(
            "6",
            System.currentTimeMillis(),
            "https://i.pinimg.com/564x/26/ab/79/26ab7951865d85e9077ef173aac36583.jpg",
            "image",
            "0",
            "seen"
        )

        val message7 = ProductMessage(
            "7",
            System.currentTimeMillis(),
            "p0",
            "product",
            "0",
            Product(
                "p0",
                "https://i.pinimg.com/564x/26/ab/79/26ab7951865d85e9077ef173aac36583.jpg",
                "Sample product",
                25000,
                25000
            ),
            "seen"
        )
        list.add(0, message0)
        list.add(0, message1)
        list.add(0, message2)
        list.add(0, message3)
        list.add(0, message4)
        list.add(0, message6)
        list.add(0, message7)

        bgScope.launch {
            Thread.sleep(3000)
            uIScope.launch {
                messages.value = list
            }
            Thread.sleep(1000)
            uIScope.launch {
                observableLatestMessage.latestMessage = LatestMessage(
                    message6.id,
                    message6.createdTime,
                    message6.content,
                    message6.contentType,
                    message6.senderId,
                    "1",
                    message6.status
                )
            }
            Thread.sleep(3000)
            uIScope.launch {
                (messages.value as ArrayList).add(0, message5)
                observableLatestMessage.latestMessage = LatestMessage(
                    message5.id,
                    message5.createdTime,
                    message5.content,
                    message5.contentType,
                    message5.senderId,
                    "1",
                    message5.status
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}