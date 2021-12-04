package com.tuan2101.ezimarket.dataclasses

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.tuan2101.ezimarket.BR

/**
 * Created by ndt2101 on 12/4/2021.
 */

/**
 * Gồm các noti:
 * + voucher hết hạn
 * + Giỏ chưa thanh toán
 * + Trạng thái đon hàng
 * - Với shop:
 * + super()
 * + Dớn hàng mới
 * + Like, bình luận bài viết
 */
class Notification(
    var id: String,
    var notificationTitle: String,
    var notificationContent: String,
    var notificationImage: String,
    var createdTime: Long,
    var tag: String,
    var idContent: String,
    var notificationStatus: Boolean
)