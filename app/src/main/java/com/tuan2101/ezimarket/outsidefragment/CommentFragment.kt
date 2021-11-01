package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.adapter.CommentAdapter
import com.tuan2101.ezimarket.databinding.CommentFragmentBinding
import com.tuan2101.ezimarket.dataclasses.*
import com.tuan2101.ezimarket.viewmodel.CommentFragmentViewModel
import com.tuan2101.ezimarket.viewmodel.CommentFragmentViewModelFactory
import com.tuan2101.ezimarket.viewmodel.NewsFeedItemFragmentViewModel
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by ndt2101 on 11/1/2021.
 */
class CommentFragment() : BottomSheetDialogFragment() {
    companion object {
        val TAG: String = "CommentFragment"
    }

    var newsFeedItemFragmentViewModel: NewsFeedItemFragmentViewModel? = null

    constructor(newsFeedItemFragmentViewModel: NewsFeedItemFragmentViewModel) : this() {
        this.newsFeedItemFragmentViewModel = newsFeedItemFragmentViewModel
    }
    lateinit var viewModel: CommentFragmentViewModel
    lateinit var adapter: CommentAdapter
    lateinit var binding: CommentFragmentBinding
    private lateinit var viewModelFactory: CommentFragmentViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflate layout
        binding = CommentFragmentBinding.inflate(inflater, container, false)
        if (newsFeedItemFragmentViewModel?.currentPost?.value != null) {
            viewModelFactory = CommentFragmentViewModelFactory(newsFeedItemFragmentViewModel!!.currentPost.value)
        } else {
            viewModelFactory = CommentFragmentViewModelFactory(dummyData())
        }
        viewModel = ViewModelProvider(this, viewModelFactory)[CommentFragmentViewModel::class.java]
        adapter = CommentAdapter()
        adapter.submitList(viewModel.post?.commentList)
        binding.rcv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rcv.adapter = adapter

        return binding.root
    }

    fun dummyData() : Post {
        return Post(
            "0",
            Shop(
                "",
                "",
                "",
                "",
                null,
                null
            ), 0,
            "",
            "",
            Product(
                "",
                "",
                "",
                0,
                0,
                0f,
                "",
                0
            ),
            Voucher(
                "",
                Shop(
                    "",
                    "",
                    "",
                    "",
                    null,
                    null
                ),
                0.0,
                "",
                Date(2021, 11, 18),
                0,
                0
            ),
            ArrayList<String>(),
            mutableListOf(
                Comment("c1",
                    Shop(
                        "0",
                        "",
                        "",
                        "",
                        null,
                        null
                    ),
                    0,
                    ""
                )
            )
        )
    }


    override fun onDestroy() {
        super.onDestroy()
        newsFeedItemFragmentViewModel?.currentPost?.value = null
    }
}