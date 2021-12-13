package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuan2101.ezimarket.adapter.LatestMessageAdapter
import com.tuan2101.ezimarket.databinding.FragmentChatBinding
import com.tuan2101.ezimarket.dataclasses.LatestMessage
import com.tuan2101.ezimarket.viewmodel.ChatFragmentViewModel

class ChatFragment : Fragment() {

    lateinit var binding: FragmentChatBinding
    lateinit var viewModel: ChatFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mAdapter = LatestMessageAdapter(LatestMessageAdapter.LatestMessageListener { detailLatestMessage ->
            findNavController().navigate(ChatFragmentDirections.actionChatFragmentToChatLogFragment(LatestMessage(
                detailLatestMessage.id,
                detailLatestMessage.createdTime,
                detailLatestMessage.content,
                detailLatestMessage.contentType,
                detailLatestMessage.senderId,
                detailLatestMessage.partnerUser.id,
                detailLatestMessage.status
            )))
        })
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[ChatFragmentViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.rcv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
        }
        viewModel.isLoading.observe(viewLifecycleOwner, {
            var i = 0
            if (it) {
                Log.i("111", "dang load")
            } else {
                Log.i("111", "load xong")
            }
        })
        viewModel.loadingMessages.observe(viewLifecycleOwner, {
            it.let {
                mAdapter.submitList(it)
            }
        })
        return binding.root
    }
}