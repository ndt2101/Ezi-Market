package com.tuan2101.ezimarket.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tuan2101.ezimarket.R

class LoginAndSignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        theme.applyStyle(R.style.Theme_HomeFragment, true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_and_sign_up)

    }
}