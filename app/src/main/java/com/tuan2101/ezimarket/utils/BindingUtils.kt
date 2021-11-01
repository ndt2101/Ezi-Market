package com.tuan2101.ezimarket.utils

import android.graphics.Paint
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.dataclasses.ParentCategory
import com.tuan2101.ezimarket.dataclasses.Product
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("setOldPrice")
fun TextView.setOldPrice(product: Product) {
    if (product.oldPrice > product.newPrice) {
        this.text = "đ ${product.oldPrice}"
        this.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        this.visibility = View.GONE
    }
}
@BindingAdapter("loadImage")
fun ImageView.loadImage(imageUrl: String) {
    Glide.with(context).load(imageUrl).into(this)
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