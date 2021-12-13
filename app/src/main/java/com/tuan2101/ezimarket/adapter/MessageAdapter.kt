package com.tuan2101.ezimarket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tuan2101.ezimarket.activities.MainActivity
import com.tuan2101.ezimarket.databinding.*
import com.tuan2101.ezimarket.dataclasses.*

/**
 * Created by ndt2101 on 12/12/2021.
 */
class MessageAdapter(val partner: DisplayUser, val observableLatestMessage: ObservableLatestMessage, val lifecycleOwner: LifecycleOwner) :
    ListAdapter<Message, RecyclerView.ViewHolder>(MessageCallBack()) {

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when {
            item.senderId == MainActivity.userId && item.contentType == "text" -> 0
            item.senderId == MainActivity.userId && item.contentType == "image" -> 1
            item.senderId != MainActivity.userId && item.contentType == "text" -> 2
            item.senderId != MainActivity.userId && item.contentType == "image" -> 3
            else -> 4 // product
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            0 -> UserTextMessageViewHolder.from(parent)
            1 -> UserImageMessageViewHolder.from(parent)
            2 -> PartnerTextMessageViewHolder.from(parent)
            3 -> PartnerImageMessageViewHolder.from(parent)
            else -> ProductMessageViewHolder.from(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserTextMessageViewHolder -> holder.bind(getItem(position), observableLatestMessage, lifecycleOwner)
            is UserImageMessageViewHolder -> holder.bind(getItem(position), observableLatestMessage, lifecycleOwner)
            is PartnerTextMessageViewHolder -> holder.bind(getItem(position), partner)
            is PartnerImageMessageViewHolder -> holder.bind(getItem(position), partner)
            is ProductMessageViewHolder -> holder.bind(getItem(position) as ProductMessage)
        }
    }

    class MessageCallBack: DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem == newItem
        }

    }

}

class PartnerTextMessageViewHolder(val binding: PartnerTextMessageItemBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): PartnerTextMessageViewHolder {
            return PartnerTextMessageViewHolder(
                PartnerTextMessageItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    fun bind(message: Message, partner: DisplayUser) {
        binding.message = message
        binding.user = partner
    }
}

class PartnerImageMessageViewHolder(val binding: PartnerImageMessageItemBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): PartnerImageMessageViewHolder {
            return PartnerImageMessageViewHolder(
                PartnerImageMessageItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    fun bind(message: Message, partner: DisplayUser) {
        binding.message = message
        binding.user = partner
    }
}

class UserTextMessageViewHolder(val binding: UserTextMessageItemBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): UserTextMessageViewHolder {
            return UserTextMessageViewHolder(
                UserTextMessageItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    fun bind(message: Message, observableLatestMessage: ObservableLatestMessage, lifecycleOwner: LifecycleOwner) {
        binding.message = message
        binding.observableLatestMessage = observableLatestMessage
        binding.lifecycleOwner = lifecycleOwner
    }
}

class UserImageMessageViewHolder(val binding: UserImageMessageItemBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): UserImageMessageViewHolder {
            return UserImageMessageViewHolder(
                UserImageMessageItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    fun bind(message: Message, observableLatestMessage: ObservableLatestMessage, lifecycleOwner: LifecycleOwner) {
        binding.message = message
        binding.observableLatestMessage = observableLatestMessage
        binding.lifecycleOwner = lifecycleOwner
    }
}

class ProductMessageViewHolder(val binding: ProductMessageItemBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): ProductMessageViewHolder {
            return ProductMessageViewHolder(
                ProductMessageItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    fun bind(productMessage: ProductMessage) {
        binding.message = productMessage
    }
}