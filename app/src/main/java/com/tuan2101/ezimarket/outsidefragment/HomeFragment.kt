package com.tuan2101.ezimarket.outsidefragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.palette.graphics.Palette
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
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

        return binding.root
    }


}