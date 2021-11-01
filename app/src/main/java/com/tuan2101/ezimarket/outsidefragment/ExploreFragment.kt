package com.tuan2101.ezimarket.outsidefragment

/**
 * Created by ndt2101 on 10/29/2021.
 */
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.adapter.ExploreFragmentViewPagerAdapter
import com.tuan2101.ezimarket.databinding.FragmentExploreBinding


class ExploreFragment() : Fragment() {

    lateinit var binding: FragmentExploreBinding
    val tabTitleList = arrayListOf<String>("Tất cả", "Mẹ và bé", "Công nghệ", "Sắc đẹp", "Yêu bếp nghiện nhà")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentExploreBinding.inflate(inflater, container, false)

        binding.viewPager.adapter = ExploreFragmentViewPagerAdapter(requireActivity())
        binding.viewPager.isUserInputEnabled = true
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitleList[position]
        }.attach()
        return binding.root
    }


}