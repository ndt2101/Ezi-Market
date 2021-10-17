package com.tuan2101.ezimarket

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.widget.ImageViewCompat
import androidx.databinding.DataBindingUtil
import com.tuan2101.ezimarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        theme.applyStyle(R.style.Theme_HomeFragment, true)
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_main)

        // set color for icon of floating action button
        ImageViewCompat.setImageTintList(
            binding.cart,
            ColorStateList.valueOf(resources.getColor(R.color.custom))
        )

    }


}