package com.tuan2101.ezimarket.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tuan2101.ezimarket.databinding.ProductItemBinding
import com.tuan2101.ezimarket.dataclasses.Product

class FlashSaleAdapter() : ListAdapter<Product, RecyclerView.ViewHolder>(ProductItemCallBack()) {


    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SaleProductViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SaleProductViewHolder).bind(getItem(position), holder)
    }

}

class ProductItemCallBack() : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id

    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

}

class SaleProductViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root), LifecycleOwner {
    companion object {
        fun from(parent: ViewGroup) : SaleProductViewHolder {
            return SaleProductViewHolder(ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    fun bind(product: Product, holder: SaleProductViewHolder) {
        binding.product = product
        binding.executePendingBindings()
        binding.lifecycleOwner = holder
    }

    override fun getLifecycle(): Lifecycle {
        return LifecycleRegistry(this)
    }
}