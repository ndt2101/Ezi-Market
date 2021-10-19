package com.tuan2101.ezimarket.outsidefragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.palette.graphics.Palette
import androidx.viewpager2.widget.ViewPager2
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.adapter.AdvertisementAdapter
import com.tuan2101.ezimarket.databinding.FragmentHomeBinding
import com.tuan2101.ezimarket.dataclasses.AdvertisementPhoto
import com.tuan2101.ezimarket.utils.DepthPageTransformer
import com.tuan2101.ezimarket.utils.ZoomOutPageTransformer


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var listAdPhoto: List<AdvertisementPhoto>
    lateinit var runnable: Runnable
    val handler = Handler(Looper.getMainLooper())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.bitmap)
        Palette.from(bitmap).generate { palette ->
            val color1 = palette?.getVibrantColor(resources.getColor(R.color.themeColor))
            if (color1 != null) {
                binding.collapsingToolbarLayout.setContentScrimColor(color1)
            }
        }

        listAdPhoto = dummyData()
        val advertisementAdapter = AdvertisementAdapter(listAdPhoto)
        binding.advertisementSlide.adapter = advertisementAdapter
        binding.ciIndicator.setViewPager(binding.advertisementSlide)

        runnable = Runnable {
            if (binding.advertisementSlide.currentItem == listAdPhoto.size - 1) {
                binding.advertisementSlide.currentItem = 0
            } else {
                binding.advertisementSlide.currentItem = binding.advertisementSlide.currentItem + 1
            }
            //                handler.postDelayed(this, 2500)
        }


        binding.advertisementSlide.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2500)
            }
        })

        binding.advertisementSlide.setPageTransformer(ZoomOutPageTransformer())

        return binding.root
    }

    fun dummyData(): ArrayList<AdvertisementPhoto> {
        val listImg = ArrayList<AdvertisementPhoto>()
        listImg.add(AdvertisementPhoto("https://photo-cms-ngaynay.zadn.vn/666x374/Uploaded/2021/uncdwpjwq/2021_06_14/shoppe-8153.jpg"))
        listImg.add(AdvertisementPhoto("https://shopeeplus.com/upload/images/L%E1%BA%AFc-L%C3%A0-Sale-Shopee.jpg"))
        listImg.add(AdvertisementPhoto("https://ben.com.vn/tin-tuc/wp-content/uploads/2021/09/cach-san-sale-0d-tren-shopee-5.png"))
        listImg.add(AdvertisementPhoto("https://channel.mediacdn.vn/thumb_w/640/2020/10/10/photo-1-16023035696791479981389.jpg"))
        listImg.add(AdvertisementPhoto("https://cafefcdn.com/thumb_w/650/pr/2020/1606892520840-0-0-375-600-crop-1606892525006-63742515071978.jpg"))
        listImg.add(AdvertisementPhoto("https://file.publish.vn/blogktcity/flash-sale-shopee-1593746001532.jpg"))
        return listImg
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 2500)
    }
}