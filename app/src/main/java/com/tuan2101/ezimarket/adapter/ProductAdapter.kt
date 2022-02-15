package com.tuan2101.ezimarket.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tuan2101.ezimarket.databinding.ProductItemBinding
import com.tuan2101.ezimarket.dataclasses.Product
import com.tuan2101.ezimarket.dataclasses.SearchedProduct

class ProductAdapter(val listener: ProductListener) : ListAdapter<SearchedProduct, ProductViewHolder>(ProductCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        return holder.bind(getItem(position), holder, listener)
    }

}

open class ProductListener(val clickProduct: (product: SearchedProduct) -> Unit) {
    fun onClickProduct(product: SearchedProduct) = clickProduct(product)
}

class ProductViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root), LifecycleOwner {
    companion object {
        fun from(parent: ViewGroup) : ProductViewHolder {
            return ProductViewHolder(ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    fun bind(product: SearchedProduct, holder: ProductViewHolder, listener: ProductListener) {
        binding.product = product
        binding.lifecycleOwner = holder
        binding.action = listener
        binding.executePendingBindings()
        Log.i("action", listener.clickProduct.toString())
    }

    override fun getLifecycle(): Lifecycle {
        return LifecycleRegistry(this)
    }
}

class ProductCallBack() : DiffUtil.ItemCallback<SearchedProduct>() {
    override fun areItemsTheSame(oldItem: SearchedProduct, newItem: SearchedProduct): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SearchedProduct, newItem: SearchedProduct): Boolean {
        return oldItem == newItem
    }
}
