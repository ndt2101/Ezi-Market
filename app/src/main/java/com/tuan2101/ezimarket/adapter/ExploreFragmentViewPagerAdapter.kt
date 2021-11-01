package com.tuan2101.ezimarket.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tuan2101.ezimarket.outsidefragment.*

/**
 * Created by ndt2101 on 10/30/2021.
 */
class ExploreFragmentViewPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> NewsFeedItemFragment("all") // ít nữa sẽ chỉnh theo child trong reference của firebase(noi lưu trữ dữ liệu bài đăng tùy theo tường tab)
            1 -> NewsFeedItemFragment("mothersAndBaby")
            2 -> NewsFeedItemFragment("technology")
            3 -> NewsFeedItemFragment("beauty")
            4 -> NewsFeedItemFragment("homeAndKitchen")
            else -> NewsFeedItemFragment("all")
        }
    }
}