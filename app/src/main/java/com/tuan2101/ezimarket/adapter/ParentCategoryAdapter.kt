package com.tuan2101.ezimarket.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.databinding.ParentCategoryItemBinding
import com.tuan2101.ezimarket.dataclasses.CategoryItem
import com.tuan2101.ezimarket.dataclasses.ParentCategory

class ParentCategoryAdapter(val parentCategoryItemClickListener: ParentCategoryItemViewHolder.ParentCategoryItemClickListener,
                            val selectedItem: MutableLiveData<ParentCategory>,
                            val lifecycleOwner: LifecycleOwner
) :
    ListAdapter<ParentCategory, ParentCategoryItemViewHolder>(ParentCategoryItemViewHolder.ParentCategoryItemCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ParentCategoryItemViewHolder {
        return ParentCategoryItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ParentCategoryItemViewHolder, position: Int) {
        holder.bind(getItem(position), parentCategoryItemClickListener, holder, selectedItem, lifecycleOwner)
    }
}

class ParentCategoryItemViewHolder(val binding: ParentCategoryItemBinding) :
    RecyclerView.ViewHolder(binding.root), LifecycleOwner {
    companion object {
        fun from(parent: ViewGroup): ParentCategoryItemViewHolder {
            return ParentCategoryItemViewHolder(
                ParentCategoryItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    fun bind(
        item: ParentCategory,
        parentCategoryItemClickListener: ParentCategoryItemClickListener, holder: ParentCategoryItemViewHolder,
        selectedItem: MutableLiveData<ParentCategory>, lifecycleOwner: LifecycleOwner
    ) {
        binding.item = item
        binding.listener = parentCategoryItemClickListener
        selectedItem.observe(lifecycleOwner, {
            if (it.id == item.id) {
                binding.shopping.background = ResourcesCompat.getDrawable(holder.binding.root.context.resources, R.drawable.selected_parent_item_background, null)
            } else {
                binding.shopping.background = ResourcesCompat.getDrawable(holder.binding.root.context.resources, R.drawable.unselected_suggest_item_bg, null)
            }
            Log.i("rrr", it.id)
        })
        binding.executePendingBindings()
    }

    override fun getLifecycle(): Lifecycle {
        return LifecycleRegistry(this)
    }

    class ParentCategoryItemClickListener(val itemClickListener: (item: ParentCategory) -> Unit) {
        fun onItemClickListener(item: ParentCategory) = itemClickListener(item)
    }

    class ParentCategoryItemCallBack : DiffUtil.ItemCallback<ParentCategory>() {
        override fun areItemsTheSame(oldItem: ParentCategory, newItem: ParentCategory): Boolean {
            return newItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: ParentCategory, newItem: ParentCategory): Boolean {
            return newItem == oldItem
        }

    }
}