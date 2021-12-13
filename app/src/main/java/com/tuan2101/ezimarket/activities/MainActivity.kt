package com.tuan2101.ezimarket.activities

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.ImageViewCompat
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    companion object {
//        val userId: String = FirebaseAuth.getInstance().currentUser?.uid ?: "userId"
    val userId: String = "0"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        theme.applyStyle(R.style.Theme_HomeFragment, true)
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_main)


//        binding.bottomNavView.clearAnimation()
//        binding.bottomNavView.animate().translationY()
    }
    
}