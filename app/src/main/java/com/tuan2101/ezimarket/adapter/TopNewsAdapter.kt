package com.tuan2101.ezimarket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tuan2101.ezimarket.databinding.TopNewsItemBinding
import com.tuan2101.ezimarket.dataclasses.News


class TopNewsAdapter(val topNewsItemClickListener: TopNewsItemClickListener) :
    ListAdapter<TopNewsAdapter.TopNewsDataItem, RecyclerView.ViewHolder>(NewsItemCallBack()) {

    class TopNewsItemClickListener(
        val clickFooter: () -> Unit,
        val clickItem: (id: String) -> Unit
    ) : FooterListener() {
        override fun onClickFooter() {
            clickFooter()
        }

        fun onClickItem(id: String) {
            clickItem(id)
        }
    }

    sealed class TopNewsDataItem(val id: String) {
        class Item(val news: News) : TopNewsDataItem(news.id)
        class Footer() : TopNewsDataItem("footer")
    }

    class NewsItemCallBack() : DiffUtil.ItemCallback<TopNewsDataItem>() {

        override fun areItemsTheSame(
            oldItem: TopNewsDataItem,
            newItem: TopNewsDataItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: TopNewsDataItem,
            newItem: TopNewsDataItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    fun customSubmitList(list: List<News>) {
        if (list.isNotEmpty()) {
            val newList = list.map { TopNewsDataItem.Item(it) } + listOf(TopNewsDataItem.Footer())
            submitList(newList)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> TopNewsViewHolder.from(parent)
            1 -> FooterViewHolder.fromFooter(parent)
            else -> throw (Exception("not release viewHolder"))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TopNewsViewHolder -> holder.bind(
                (getItem(position) as TopNewsDataItem.Item).news,
                holder,
                topNewsItemClickListener
            )
            is FooterViewHolder -> holder.bindFooter(topNewsItemClickListener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is TopNewsDataItem.Item -> 0
            is TopNewsDataItem.Footer -> 1
        }
    }
}


class TopNewsViewHolder(val binding: TopNewsItemBinding) : RecyclerView.ViewHolder(binding.root),
    LifecycleOwner {
    companion object {
        fun from(parent: ViewGroup): TopNewsViewHolder {
            return TopNewsViewHolder(
                TopNewsItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    fun bind(
        news: News,
        holder: TopNewsViewHolder,
        listener: TopNewsAdapter.TopNewsItemClickListener
    ) {
        binding.news = news
        binding.lifecycleOwner = holder
        binding.action = listener
        binding.executePendingBindings()
    }

    override fun getLifecycle(): Lifecycle {
        return LifecycleRegistry(this)
    }
}

abstract class FooterListener() {
    abstract fun onClickFooter()
}