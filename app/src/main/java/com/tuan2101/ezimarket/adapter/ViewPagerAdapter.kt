package com.tuan2101.ezimarket.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tuan2101.ezimarket.outsidefragment.CategoryFragment
import com.tuan2101.ezimarket.outsidefragment.HomeFragment
import com.tuan2101.ezimarket.outsidefragment.IndividualFragment
import com.tuan2101.ezimarket.outsidefragment.NewFeedFragment

class ViewPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> HomeFragment()
            1 -> CategoryFragment()
            2 -> NewFeedFragment()
            3 -> IndividualFragment()
            else -> HomeFragment()
        }
    }
}