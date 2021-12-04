package com.tuan2101.ezimarket.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuan2101.ezimarket.dataclasses.Notification

/**
 * Created by ndt2101 on 12/4/2021.
 */
class NotificationFragmentViewModel : ViewModel() {
    var notifications = MutableLiveData<List<Notification>>()
    val selectedNotification = MutableLiveData<Notification>()
    init {
        notifications.value = dummyData()
        Log.i("NotificationViewModel", "init")
    }

    fun onSelectNotification(notification: Notification) {
        notification.notificationStatus = true
        selectedNotification.value = notification
    }

    fun dummyData(): ArrayList<Notification> {
        val list = ArrayList<Notification>()
        list.add(
            Notification(
                "a",
                "Nguyen Tuan da like bai viet cua ban",
                "Nguyen Tuan da like bai viet: Mot con vit xoe ra 2 cai canh",
                "https://scontent.fhan3-3.fna.fbcdn.net/v/t39.30808-6/258824500_3213648145535890_6988455686604487916_n.jpg?_nc_cat=1&ccb=1-5&_nc_sid=730e14&_nc_ohc=36S7H265ZQwAX9D5nl5&_nc_ht=scontent.fhan3-3.fna&oh=12f782a4b10e1f463e6c17b6f9ab9992&oe=61B10A17",
                System.currentTimeMillis(),
                "postNotification",
                "post1",
                true
            )
        )

        list.add(
            Notification(
                "b",
                "Nguyen Tuan da binh luan bai viet cua ban",
                "Nguyen Tuan da like bai viet: Mot con vit xoe ra 2 cai canh",
                "https://scontent.fhan3-3.fna.fbcdn.net/v/t39.30808-6/258824500_3213648145535890_6988455686604487916_n.jpg?_nc_cat=1&ccb=1-5&_nc_sid=730e14&_nc_ohc=36S7H265ZQwAX9D5nl5&_nc_ht=scontent.fhan3-3.fna&oh=12f782a4b10e1f463e6c17b6f9ab9992&oe=61B10A17",
                System.currentTimeMillis(),
                "postNotification",
                "post1",
                false
            )
        )

        list.add(
            Notification(
                "c",
                "Ban co voucher sap het han",
                "Voucher giam 30% khi mua don hang 2 trieu dong cua shop Dummy sap het han",
                "https://scontent.fhan3-3.fna.fbcdn.net/v/t39.30808-6/258824500_3213648145535890_6988455686604487916_n.jpg?_nc_cat=1&ccb=1-5&_nc_sid=730e14&_nc_ohc=36S7H265ZQwAX9D5nl5&_nc_ht=scontent.fhan3-3.fna&oh=12f782a4b10e1f463e6c17b6f9ab9992&oe=61B10A17",
                System.currentTimeMillis(),
                "voucherNotification",
                "voucher1",
                false
            )
        )

        list.add(
            Notification(
                "d",
                "Nguyen Dinh da like bai viet cua ban",
                "Nguyen Dinh da like bai viet: Mot con vit xoe ra 2 cai canh",
                "https://scontent.fhan3-3.fna.fbcdn.net/v/t39.30808-6/258824500_3213648145535890_6988455686604487916_n.jpg?_nc_cat=1&ccb=1-5&_nc_sid=730e14&_nc_ohc=36S7H265ZQwAX9D5nl5&_nc_ht=scontent.fhan3-3.fna&oh=12f782a4b10e1f463e6c17b6f9ab9992&oe=61B10A17",
                System.currentTimeMillis(),
                "postNotification",
                "post1",
                false
            )
        )

        list.add(
            Notification(
                "e",
                "Nguyen Dinh da binh luan bai viet cua ban",
                "Nguyen Dinh da like bai viet: Mot con vit xoe ra 2 cai canh",
                "https://scontent.fhan3-3.fna.fbcdn.net/v/t39.30808-6/258824500_3213648145535890_6988455686604487916_n.jpg?_nc_cat=1&ccb=1-5&_nc_sid=730e14&_nc_ohc=36S7H265ZQwAX9D5nl5&_nc_ht=scontent.fhan3-3.fna&oh=12f782a4b10e1f463e6c17b6f9ab9992&oe=61B10A17",
                System.currentTimeMillis(),
                "postNotification",
                "post1",
                false
            )
        )
        return list
    }
}