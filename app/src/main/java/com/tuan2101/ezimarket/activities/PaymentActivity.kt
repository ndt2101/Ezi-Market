package com.tuan2101.ezimarket.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity() {

    lateinit var binding: ActivityPaymentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        theme.applyStyle(R.style.Theme_HomeFragment, true)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this ,R.layout.activity_payment)
    }
}