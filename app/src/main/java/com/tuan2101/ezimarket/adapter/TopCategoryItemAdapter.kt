package com.tuan2101.ezimarket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tuan2101.ezimarket.databinding.CategoryItemBinding
import com.tuan2101.ezimarket.dataclasses.CategoryItem

class TopCategoryItemAdapter(val topCategoryItemClickListener: TopCategoryItemViewHolder.TopCategoryItemClickListener) :
    ListAdapter<CategoryItem, TopCategoryItemViewHolder>(TopCategoryItemViewHolder.TopCategoryItemCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopCategoryItemViewHolder {
        return TopCategoryItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TopCategoryItemViewHolder, position: Int) {
        holder.bind(getItem(position), topCategoryItemClickListener)
    }

}

class TopCategoryItemViewHolder(val binding: CategoryItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): TopCategoryItemViewHolder {
            return TopCategoryItemViewHolder(
                CategoryItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    fun bind(item: CategoryItem, topCategoryItemClickListener: TopCategoryItemClickListener) {
        binding.item = item
        binding.listener = topCategoryItemClickListener
        binding.executePendingBindings()
    }

    class TopCategoryItemClickListener(val itemClickListener: (id: String) -> Unit) {
        fun onItemClickListener(id: String) = itemClickListener(id)
    }

    class TopCategoryItemCallBack : DiffUtil.ItemCallback<CategoryItem>() {
        override fun areItemsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
            return newItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
            return newItem == oldItem
        }

    }
}