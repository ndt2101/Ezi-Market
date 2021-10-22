package com.tuan2101.ezimarket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tuan2101.ezimarket.databinding.CategoryItemBinding
import com.tuan2101.ezimarket.dataclasses.CategoryItem

class TopCategoryItemAdapter(val listItem: List<CategoryItem>, val topCategoryItemClickListener: TopCategoryItemViewHolder.TopCategoryItemClickListener) :
    RecyclerView.Adapter<TopCategoryItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopCategoryItemViewHolder {
        return TopCategoryItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TopCategoryItemViewHolder, position: Int) {
        holder.bind(listItem[position], topCategoryItemClickListener)
    }

    override fun getItemCount(): Int {
        return listItem.size
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

    class TopCategoryItemClickListener(val itemClickListener: (id: Int) -> Unit) {
        fun onItemClickListener(id: Int) = itemClickListener(id)
    }
}