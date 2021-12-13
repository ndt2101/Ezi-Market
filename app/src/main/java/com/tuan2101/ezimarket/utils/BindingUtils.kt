package com.tuan2101.ezimarket.utils

import android.graphics.Paint
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.dataclasses.*
import de.hdodenhof.circleimageview.CircleImageView
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("setOldPrice")
fun TextView.setOldPrice(product: Product) {
    if (product.oldPrice > product.newPrice) {
        this.text = "${product.oldPrice} đ"
        this.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        this.visibility = View.GONE
    }
}
@BindingAdapter("loadImage")
fun ImageView.loadImage(imageUrl: String?) {
    if (imageUrl != null) {
        Glide.with(context).load(imageUrl).into(this)
    }
}

@BindingAdapter("customSetBackground1")
fun TextView.customSetBackground1(subject: LiveData<String>) {
    if (subject.value == "1") {
        this.background = ResourcesCompat.getDrawable(resources, R.drawable.selected_dr1, null)
    } else {
        this.background = ResourcesCompat.getDrawable(resources, R.drawable.unselected_dr, null)
    }
}

@BindingAdapter("customSetBackground2")
fun TextView.customSetBackground2(subject: LiveData<String>) {
    if (subject.value == "2") {
        this.background = ResourcesCompat.getDrawable(resources, R.drawable.selected_dr2, null)
    } else {
        this.background = ResourcesCompat.getDrawable(resources, R.drawable.unselected_dr, null)
    }
}

@BindingAdapter("loadImageFromResource")
fun ShapeableImageView.loadImageFromResource(src: Int) {
    this.setImageResource(src)
} @BindingAdapter("showNumOfSold")
fun TextView.showNumOfSold(num: Long) {
    var newNum: Double = num.toDouble()
    if (num >= 1000) {
        newNum = num / 1000.0
        this.text = "Đã bán ${String.format("%.1f", newNum)}k"
    } else {
        this.text = "Đã bán ${num.toInt()}"
    }
}

// setSelectedItemBackground
@BindingAdapter("itemId", "currentItem")
fun RelativeLayout.setSelectedItemBackground(id: String ,itemId: MutableLiveData<ParentCategory>) {
    if (itemId.value != null) {
        if (itemId.value!!.id == id) {
            background = ResourcesCompat.getDrawable(resources, R.drawable.selected_suggest_item_bg, null)
        }
    } else {
        background = ResourcesCompat.getDrawable(resources, R.drawable.unselected_suggest_item_bg, null)
    }

    Log.i("rrr", id)
    itemId.value?.id?.let { Log.i("rr", it) }
}

@BindingAdapter("setTotalPrice")
fun TextView.setTotalPrice(totalPrice: Long) {
    if (totalPrice == 0L) {
        textSize = resources.getDimension(R.dimen.small)
        text = "Vui lòng chọn sản phẩm"
    } else {
        text = "$totalPrice đ"
        textSize = resources.getDimension(R.dimen.large)
    }
}

@BindingAdapter("dateFormat")
fun TextView.dateFormat(date: Date) {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    text ="Hạn sử dụng: ${simpleDateFormat.format(date)}"
}

@BindingAdapter("receivedDate")
fun TextView.receivedDate(date: Date?) {
    if (date != null){
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        text = "Ngày nhận hàng: ${simpleDateFormat.format(date)}"
    }
}

@BindingAdapter("setVoucher")
fun TextView.setVoucher(voucher: MutableLiveData<PostVoucher>) {
    if (voucher.value == null) {
        text = "Chọn voucher"
    } else {
        this.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, R.drawable.coupon_icon), null, null, null)
        text = "Đã được giảm ${(voucher.value!!.discount * 100).toInt()}%"
    }
}

@BindingAdapter("setVoucher1")
fun TextView.setVoucher1(voucher: Voucher?) {
    text = if (voucher == null) {
        "Chọn voucher"
    } else {
        this.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, R.drawable.coupon_icon), null, null, null)
        "Đã được giảm ${(voucher.discount * 100).toInt()}%"
    }
}

@BindingAdapter("setShippingMethodVisibility")
fun ViewGroup.setShippingMethodVisibility(bill: Bill) {
    if (bill.shippingMethod == null) {
        visibility = View.GONE
    }
}

@BindingAdapter("payWhenReceive")
fun RadioButton.payWhenReceive(method: MutableLiveData<String>) {
    this.isChecked = method.value == "Thanh toán khi nhận hàng"
}


@BindingAdapter("customizeVisibility1")
fun View.customizeVisibility1(status: String) {
    if (status == "") {
        visibility = View.GONE
    }
}

@BindingAdapter("customizeVisibility2")
fun View.customizeVisibility2(status: String) {
    if (status != "") {
        visibility = View.GONE
    }
}

@BindingAdapter("notificationBackground")
fun ConstraintLayout.notificationBackground(notification: Notification) {
    background = if (notification.notificationStatus) {
        ResourcesCompat.getDrawable(resources, R.color.white, null)
    } else {
        ResourcesCompat.getDrawable(resources, R.color.notification_background, null)
    }
}
