package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuan2101.ezimarket.adapter.MessageAdapter
import com.tuan2101.ezimarket.databinding.FragmentChatLogBinding
import com.tuan2101.ezimarket.dataclasses.LatestMessage
import com.tuan2101.ezimarket.viewmodel.ChatLogFragmentViewModel
import com.tuan2101.ezimarket.viewmodel.ChatLogFragmentViewModelFactory

class ChatLogFragment() : Fragment() {

    lateinit var binding: FragmentChatLogBinding
    lateinit var viewModel: ChatLogFragmentViewModel
    lateinit var latestMessage: LatestMessage
    lateinit var adapter: MessageAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChatLogBinding.inflate(inflater, container, false)
        latestMessage = ChatLogFragmentArgs.fromBundle(requireArguments()).latestMessage
        val factory = ChatLogFragmentViewModelFactory(latestMessage)
        viewModel = ViewModelProvider(this, factory)[ChatLogFragmentViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        layoutManager.reverseLayout = true
        binding.messages.layoutManager = layoutManager
        viewModel.partner.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter = MessageAdapter(
                    viewModel.partner.value!!,
                    viewModel.observableLatestMessage,
                    viewLifecycleOwner,
                    MessageAdapter.MessageListener { imageUrl ->
                        findNavController().navigate(
                            ChatLogFragmentDirections.actionChatLogFragmentToImageDetailFragment(
                                imageUrl
                            )
                        )
                    })
                binding.messages.adapter = adapter
            }
        }

        viewModel.messages.observe(viewLifecycleOwner) {
            if (it.isNotEmpty() && it != null) {
                adapter.submitList(it)
                layoutManager.scrollToPositionWithOffset(it.size - 1, 0)
            }
        }

        return binding.root
    }

}