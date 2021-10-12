package com.tuan2101.ezimarket

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.tuan2101.ezimarket.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("sharePreference", MODE_PRIVATE)
        val isTheFirstTime = sharedPreferences.getString("isTheFirstTime", null)

        binding = DataBindingUtil.setContentView(this , R.layout.activity_splash)
        binding.appLogo.animation = AnimationUtils.loadAnimation(this, R.anim.splash_translation)
        if (isTheFirstTime != null) {
            handler.postDelayed({
                navigateToMain()
            }, 3500)
        } else {
            showIntro()
        }
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showIntro() {
        val listIntroPhoto: ArrayList<IntroPhoto> = addList()
        val introRunnable = Runnable {
            binding.introLayout.visibility = View.VISIBLE
            binding.splashLayout.visibility = View.GONE
            binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    if (binding.viewPager.currentItem == listIntroPhoto.size - 1) {
                        binding.circleIndicator.visibility = View.GONE
                        binding.button.visibility = View.VISIBLE
                    } else {
                        binding.circleIndicator.visibility = View.VISIBLE
                        binding.button.visibility = View.GONE
                    }
                }
            })
        }
        val introAdapter = IntroAdapter(listIntroPhoto)
        binding.viewPager.adapter = introAdapter
        binding.circleIndicator.setViewPager(binding.viewPager)
        handler.postDelayed(introRunnable, 3500)
        binding.button.setOnClickListener {
            val saveSharedPreferencesState = sharedPreferences.edit().putString("isTheFirstTime", "No").commit()
            Log.i("saveState", saveSharedPreferencesState.toString())
            navigateToMain()
        }
    }

    private fun addList() : ArrayList<IntroPhoto> {
        val arrayList = ArrayList<IntroPhoto>()
        arrayList.add(IntroPhoto(resources.getString(R.string.Chao), R.drawable.logo_transparent))
        arrayList.add(IntroPhoto(resources.getString(R.string.mua_sam_de_dang), R.drawable.mua_sam_de_dang))
        arrayList.add(IntroPhoto(resources.getString(R.string.da_dang_mat_hang), R.drawable.da_dang))
        arrayList.add(IntroPhoto(resources.getString(R.string.bat_thong_bao), R.drawable.bell))
        return arrayList
    }
}