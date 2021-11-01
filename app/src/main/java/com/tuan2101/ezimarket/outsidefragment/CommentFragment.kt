package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tuan2101.ezimarket.adapter.CommentAdapter
import com.tuan2101.ezimarket.databinding.CommentFragmentBinding
import com.tuan2101.ezimarket.viewmodel.CommentFragmentViewModel
import com.tuan2101.ezimarket.viewmodel.CommentFragmentViewModelFactory
import com.tuan2101.ezimarket.viewmodel.NewsFeedItemFragmentViewModel

/**
 * Created by ndt2101 on 11/1/2021.
 */
class CommentFragment() : BottomSheetDialogFragment() {
    companion object {
        val TAG: String = "CommentFragment"
    }

    lateinit var viewModel: CommentFragmentViewModel
    lateinit var adapter: CommentAdapter
    lateinit var binding: CommentFragmentBinding
    lateinit var viewModelFactory: CommentFragmentViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflate layout
        binding = CommentFragmentBinding.inflate(inflater, container, false)

        if (NewsFeedItemFragmentViewModel.currentPost.value != null) {
            viewModelFactory = CommentFragmentViewModelFactory(NewsFeedItemFragmentViewModel.currentPost.value!!)
        } else {
            viewModelFactory = CommentFragmentViewModelFactory(NewsFeedItemFragmentViewModel.oldCurrentPost.value!!)
        }


        viewModel = ViewModelProvider(this, viewModelFactory!!)[CommentFragmentViewModel::class.java]
        adapter = CommentAdapter()
        adapter.submitList(viewModel.post.commentList)
        binding.rcv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rcv.adapter = adapter

        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        NewsFeedItemFragmentViewModel.currentPost.value = null
    }
}