package com.tuan2101.ezimarket.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tuan2101.ezimarket.databinding.ProductViaShopInCartBinding
import com.tuan2101.ezimarket.dataclasses.ProductViaShopInCart
import com.tuan2101.ezimarket.viewmodel.CartFragmentViewModel

/**
 * Created by ndt2101 on 11/4/2021.
 */
class CartAdapter(val clickListener: ClickListener, val model: CartFragmentViewModel, val lifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<CartAdapter.ProductViaShopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViaShopViewHolder {

        return ProductViaShopViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ProductViaShopViewHolder, position: Int) {
        holder.bind(clickListener, model, position, lifecycleOwner)
    }

    override fun getItemCount(): Int {
        return model.listProductInCart.value?.size ?: 0
    }

    class ProductViaShopViewHolder(val binding: ProductViaShopInCartBinding) :
        RecyclerView.ViewHolder(binding.root), LifecycleOwner {
        companion object {
            fun from(parent: ViewGroup): ProductViaShopViewHolder {
                return ProductViaShopViewHolder(
                    ProductViaShopInCartBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )
            }
        }

        fun bind(clickListener: ClickListener, model: CartFragmentViewModel, position: Int, lifecycleOwner: LifecycleOwner) {
            val cartViaShopAdapter = CartViaShopAdapter(model, position, lifecycleOwner)
            binding.productViaShopRcv.layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
            binding.productViaShopRcv.adapter = cartViaShopAdapter
            binding.listener = clickListener
            binding.lifecycleOwner = lifecycleOwner
            binding.productViaShopInCart = model.listProductInCart.value?.get(position)?.value
            binding.executePendingBindings()
        }

        override fun getLifecycle(): Lifecycle {
            return LifecycleRegistry(this)
        }
    }

    class ClickListener(
        val clickAllProductViaShop: (shopId: String) -> Unit,
        val clickVisitShop: (shopId: String) -> Unit,
        val clickSelectVoucher: (shopId: String) -> Unit
    ) {
        fun onSelectAllProductViaShop(shopId: String) = clickAllProductViaShop(shopId)
        fun onClickVisitShop(shopId: String) = clickVisitShop(shopId)
        fun onClickSelectVoucher(shopId: String) = clickSelectVoucher(shopId)
    }


}