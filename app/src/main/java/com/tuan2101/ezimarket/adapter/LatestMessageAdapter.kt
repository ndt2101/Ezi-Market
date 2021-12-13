package com.tuan2101.ezimarket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tuan2101.ezimarket.databinding.LatestMessageItemBinding
import com.tuan2101.ezimarket.dataclasses.DetailLatestMessage
import com.tuan2101.ezimarket.dataclasses.LatestMessage

/**
 * Created by ndt2101 on 12/11/2021.
 */
class LatestMessageAdapter(val listener: LatestMessageListener) : ListAdapter<DetailLatestMessage, LatestMessageAdapter.LatestMessageViewHolder>(MessageCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestMessageViewHolder {
        return LatestMessageViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: LatestMessageViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class LatestMessageViewHolder(val binding: LatestMessageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): LatestMessageViewHolder {
                return LatestMessageViewHolder(LatestMessageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }

        fun bind(message: DetailLatestMessage, listener: LatestMessageListener) {
            binding.message = message
            binding.listener = listener
        }
    }

    class MessageCallBack : DiffUtil.ItemCallback<DetailLatestMessage>() {
        override fun areItemsTheSame(oldItem: DetailLatestMessage, newItem: DetailLatestMessage): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DetailLatestMessage, newItem: DetailLatestMessage): Boolean {
            return oldItem == newItem
        }

    }

    class LatestMessageListener(val clickLatestMessage: (latestMessage: DetailLatestMessage) -> Unit) {
        fun onClickLatestMessage(latestMessage: DetailLatestMessage) = clickLatestMessage(latestMessage)
    }
}

