package com.tuan2101.ezimarket.utils

import android.graphics.Paint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.tuan2101.ezimarket.R

@BindingAdapter("setOldPrice")
fun TextView.setOldPrice(price: Long) {
    this.text = "đ $price"
    this.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
}
@BindingAdapter("loadImage")
fun ImageView.loadImage(imageUrl: String) {
    Glide.with(context).load(imageUrl).into(this)
}
