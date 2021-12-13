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
                    "https://nude.vn/wp-content/uploads/2021/01/ngoc-trinh-nude-art-02-360x540.jpg",
                    "Ngoc Trinh",
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
            "Build a bitch1",
            "text",
            "0",
            "sent"
        )

        val message1 = Message(
            "1",
            System.currentTimeMillis(),
            "https://nude.vn/wp-content/uploads/2021/01/ngoc-trinh-nude-art-02-360x540.jpg",
            "image",
            "1",
            "sent"
        )
        val message2 = Message(
            "2",
            System.currentTimeMillis(),
            "Build a bitch",
            "text",
            "0",
            "sent"
        )

        val message3 = Message(
            "3",
            System.currentTimeMillis(),
            "dit khong????",
            "text",
            "1",
            "sent"
        )

        val message4 = Message(
            "4",
            System.currentTimeMillis(),
            "https://nude.vn/wp-content/uploads/2021/01/ngoc-trinh-nude-art-02-360x540.jpg",
            "image",
            "0",
            "sent"
        )

        val message5 = Message(
            "5",
            System.currentTimeMillis(),
            "cuoi",
            "text",
            "0",
            "sent"
        )

        val message6 = Message(
            "6",
            System.currentTimeMillis(),
            "https://nude.vn/wp-content/uploads/2021/01/ngoc-trinh-nude-art-02-360x540.jpg",
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
                "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/248384203_1067157620782867_6617827598330014721_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=VavUaKnuwFYAX9aToDm&_nc_ht=scontent.fhph1-1.fna&oh=92e07c5c07854ba1c60973f97c5e0ae3&oe=61A530BC",
                "Thùy Dương product",
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