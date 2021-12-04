package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuan2101.ezimarket.adapter.NotificationAdapter
import com.tuan2101.ezimarket.databinding.FragmentNotificationBinding
import com.tuan2101.ezimarket.viewmodel.NotificationFragmentViewModel

class NotificationFragment : Fragment() {
    lateinit var binding: FragmentNotificationBinding
    lateinit var viewModel: NotificationFragmentViewModel
    lateinit var adapter: NotificationAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[NotificationFragmentViewModel::class.java]
        adapter = NotificationAdapter(NotificationAdapter.NotificationClickListener {
                notification -> viewModel.onSelectNotification(notification)
        }, viewLifecycleOwner)
        binding.notificationList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.notificationList.adapter = adapter
        viewModel.notifications.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                adapter.submitList(it)
            }
        })

        viewModel.selectedNotification.observe(viewLifecycleOwner, {
            if (it != null) {
//                Log.i("notificationDetail", it.idContent + "${it.notificationStatus}")
//                findNavController().navigate(NotificationFragmentDirections.actionNotificationFragmentToCartFragment2())
                viewModel.selectedNotification.value = null
            }
        })
        return binding.root
    }
}