package com.tuan2101.ezimarket.outsidefragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationBarView
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.adapter.ViewPagerAdapter
import com.tuan2101.ezimarket.databinding.FragmentHostBinding
import com.tuan2101.ezimarket.utils.DepthPageTransformer


class HostFragment : Fragment() {

    lateinit var binding: FragmentHostBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHostBinding.inflate(inflater ,container, false)

        // set color for icon of floating action button
//        ImageViewCompat.setImageTintList(
//            binding.cart,
//            ColorStateList.valueOf(resources.getColor(R.color.custom))
//        )

        binding.viewpager.isUserInputEnabled = false
        binding.viewpager.adapter = ViewPagerAdapter(requireActivity())
        binding.viewpager. registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> binding.bottomNavView.menu.findItem(R.id.menu_home).isChecked = true
                    1 -> binding.bottomNavView.menu.findItem(R.id.menu_category).isChecked = true
                    2 -> binding.bottomNavView.menu.findItem(R.id.menu_new_feed).isChecked = true
                    3 -> binding.bottomNavView.menu.findItem(R.id.menu_individual).isChecked = true
                }
            }
        })


        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> binding.viewpager.currentItem = 0
                R.id.menu_category -> binding.viewpager.currentItem = 1
                R.id.menu_new_feed -> binding.viewpager.currentItem = 2
                R.id.menu_individual -> binding.viewpager.currentItem = 3
            }
            true
        }

        binding.cart.setOnClickListener {
            findNavController().navigate(HostFragmentDirections.actionHostFragmentToCartFragment())
        }

        binding.viewpager.setPageTransformer(DepthPageTransformer())
        return binding.root
    }

}