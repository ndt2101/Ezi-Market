package com.tuan2101.ezimarket.dataclasses

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.tuan2101.ezimarket.BR
import kotlinx.parcelize.Parcelize

/**
 * Created by ndt2101 on 12/11/2021.
 */

@Parcelize
open class Message(
    val id: String,
    val createdTime: Long,
    val content: String,
    val contentType: String,
    val senderId: String,
    var status: String
): Parcelable {
    override fun equals(other: Any?): Boolean {
        return other is LatestMessage &&
                createdTime == createdTime
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + createdTime.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + contentType.hashCode()
        result = 31 * result + senderId.hashCode()
        result = 31 * result + status.hashCode()
        return result
    }
}


class LatestMessage(
    id: String,
    createdTime: Long,
    content: String,
    contentType: String,
    senderId: String,
    val partnerId: String,
    status: String
) : Message(id, createdTime, content, contentType, senderId, status)

class DetailLatestMessage(
    id: String,
    createdTime: Long,
    content: String,
    contentType: String,
    senderId: String,
    val partnerUser: DisplayUser,
    status: String
) : Message(id, createdTime, content, contentType, senderId, status)

class ProductMessage(
    id: String,
    createdTime: Long,
    content: String,
    contentType: String,
    senderId: String,
    val product: Product,
    status: String
) : Message(id, createdTime, content, contentType, senderId, status)

class ObservableLatestMessage(latestMessage: LatestMessage): BaseObservable() {
    @get:Bindable
    var latestMessage: LatestMessage = latestMessage
        set(value) {
            field = value
            notifyPropertyChanged(BR.latestMessage)
        }
}