package com.tuan2101.ezimarket.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tuan2101.ezimarket.databinding.FooterBinding
import com.tuan2101.ezimarket.databinding.ProductItemBinding
import com.tuan2101.ezimarket.dataclasses.Product

class FlashSaleAdapter(val listener: ProductItemClickListener) : ListAdapter<FlashSaleAdapter.DataItem, RecyclerView.ViewHolder>(ProductItemCallBack()) {


    fun customSubmitList(list: List<Product>) {
        if (list.isNotEmpty()) {
           val newList = list.map { DataItem.Item(it) } + listOf(DataItem.Footer())
            submitList(newList)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is DataItem.Item -> 0
            is DataItem.Footer -> 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            0 -> SaleProductViewHolder.from(parent)
            1 -> FooterViewHolder.fromFooter(parent)
            else -> throw (Exception("not release viewHolder"))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SaleProductViewHolder -> holder.bind((getItem(position) as DataItem.Item).product, holder, listener)
            is FooterViewHolder -> holder.bindFooter(listener)
        }
    }

    sealed class DataItem(val id: String) {
        class Item(val product: Product): DataItem(product.id)
        class Footer() : DataItem("footer")
    }

}

class ProductItemCallBack() : DiffUtil.ItemCallback<FlashSaleAdapter.DataItem>() {

    override fun areItemsTheSame(
        oldItem: FlashSaleAdapter.DataItem,
        newItem: FlashSaleAdapter.DataItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: FlashSaleAdapter.DataItem,
        newItem: FlashSaleAdapter.DataItem
    ): Boolean {
        return oldItem == newItem
    }

}

class SaleProductViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root), LifecycleOwner {
    companion object {
        fun from(parent: ViewGroup) : SaleProductViewHolder {
            return SaleProductViewHolder(ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    fun bind(product: Product, holder: SaleProductViewHolder, listener: ProductItemClickListener) {
        binding.product = product
        binding.executePendingBindings()
        binding.lifecycleOwner = holder
        binding.action = listener
        Log.i("action", listener.clickItem.toString())
    }

    override fun getLifecycle(): Lifecycle {
        return LifecycleRegistry(this)
    }
}

class FooterViewHolder(val binding: FooterBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun fromFooter(parent: ViewGroup) : FooterViewHolder {
            return FooterViewHolder(FooterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    fun bindFooter(listener: ProductItemClickListener) {
        binding.action = listener
    }
}

class ProductItemClickListener(val clickFooter: () -> Unit, val clickItem: (id: String) -> Unit) {
    fun onClickFooter() {
        clickFooter()
    }
    fun onClickItem(id: String) {
        clickItem(id)
    }
}


