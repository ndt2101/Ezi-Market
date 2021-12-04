package com.tuan2101.ezimarket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tuan2101.ezimarket.databinding.NotificationItemBinding
import com.tuan2101.ezimarket.dataclasses.Notification

/**
 * Created by ndt2101 on 12/4/2021.
 */
class NotificationAdapter(val listener: NotificationClickListener, val lifecycleOwner: LifecycleOwner) : ListAdapter<Notification, NotificationAdapter.NotificationViewHolder>(NotificationCallBack()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(getItem(position), lifecycleOwner, listener)
    }

    class NotificationViewHolder(val binding: NotificationItemBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): NotificationViewHolder {
                return NotificationViewHolder(
                    NotificationItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

        fun bind(notification: Notification, lifecycleOwner: LifecycleOwner, listener: NotificationClickListener) {
            binding.notification = notification
            binding.listener = listener
            binding.lifecycleOwner = lifecycleOwner
        }
    }

    class NotificationCallBack(): DiffUtil.ItemCallback<Notification>() {
        override fun areItemsTheSame(oldItem: Notification, newItem: Notification): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Notification, newItem: Notification): Boolean {
            return oldItem.notificationStatus == newItem.notificationStatus
        }
    }

    class NotificationClickListener(val clickNotification: (notification: Notification) -> Unit) {
        fun onClickNotification(notification: Notification) = clickNotification(notification)
    }
}