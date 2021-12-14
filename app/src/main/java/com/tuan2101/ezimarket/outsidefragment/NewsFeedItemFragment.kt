package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.adapter.PostAdapter
import com.tuan2101.ezimarket.databinding.FragmentNewsFeedItemBinding
import com.tuan2101.ezimarket.dataclasses.*
import com.tuan2101.ezimarket.viewmodel.NewsFeedItemFragmentViewModel
import java.util.*
import kotlin.collections.ArrayList

class NewsFeedItemFragment() : Fragment() {

    lateinit var viewModel: NewsFeedItemFragmentViewModel
    lateinit var adapter: PostAdapter
    lateinit var commentFragment: CommentFragment
    var type: String = ""

    constructor(type: String) : this() {
        this.type = type
    }

    lateinit var binding: FragmentNewsFeedItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsFeedItemBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[NewsFeedItemFragmentViewModel::class.java]


        Log.i("FragmentType", type)
        adapter = PostAdapter(
            dummyDataForPost(),
            PostAdapter.OnPostClickListener { post -> viewModel.setCurrentPost(post) })
        binding.rcv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rcv.adapter = adapter

        viewModel.currentPost.observe(viewLifecycleOwner, {
            if (it != null) {
                commentFragment = CommentFragment(viewModel.currentPost.value!!)
                commentFragment.show(childFragmentManager, CommentFragment.TAG)
            }
        })

        return binding.root
    }

    fun dummyDataForPost(): ArrayList<Post> {
        val list = ArrayList<Post>()
        val cal = Calendar.getInstance()
        cal[Calendar.YEAR] = 2021
        cal[Calendar.MONTH] = Calendar.NOVEMBER
        cal[Calendar.DAY_OF_MONTH] = 16
        val date: Date = cal.time

        Log.i("date", "$date")

        list.add(
            Post(
                "0",
                DisplayUser(
                    "u1",
                    "https://i.pinimg.com/564x/26/ab/79/26ab7951865d85e9077ef173aac36583.jpg",                    "Thùy Dương",
                    "shop",
                    true
                ), System.currentTimeMillis(),
                resources.getString(R.string.test_text),
                "https://i.pinimg.com/564x/26/ab/79/26ab7951865d85e9077ef173aac36583.jpg",                Product(
                    "p0",
                    "https://i.pinimg.com/564x/26/ab/79/26ab7951865d85e9077ef173aac36583.jpg",                    "Thùy Dương product",
                    25000,
                    25000
                ),
                PostVoucher(
                    "v1",
                    0.3,
                    "Giảm 30% khi mua đơn hàng từ 20 triệu VND",
                    date,
                    20000000,
                    false,
                    "https://i.pinimg.com/564x/26/ab/79/26ab7951865d85e9077ef173aac36583.jpg",                    ),
                ArrayList<String>(),
                6
            )
        )

        list.add(
            Post(
                "0",
                DisplayUser(
                    "u1",
                    "https://i.pinimg.com/564x/26/ab/79/26ab7951865d85e9077ef173aac36583.jpg",                    "Thùy Dương",
                    "shop",
                    true
                ), System.currentTimeMillis(),
                resources.getString(R.string.test_text),
                "https://i.pinimg.com/564x/26/ab/79/26ab7951865d85e9077ef173aac36583.jpg",                Product(
                    "p0",
                    "https://i.pinimg.com/564x/26/ab/79/26ab7951865d85e9077ef173aac36583.jpg",                    "Thùy Dương product",
                    25000,
                    25000
                ),
                PostVoucher(
                    "v1",
                    0.3,
                    "Giảm 30% khi mua đơn hàng từ 20 triệu VND",
                    date,
                    20000000,
                    false,
                    "https://i.pinimg.com/564x/26/ab/79/26ab7951865d85e9077ef173aac36583.jpg",                    ),
                ArrayList<String>(),
                6
            )
        )

        return list
    }
}