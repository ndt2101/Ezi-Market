package com.tuan2101.ezimarket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tuan2101.ezimarket.databinding.ProductInCartItemBinding
import com.tuan2101.ezimarket.dataclasses.Product
import com.tuan2101.ezimarket.viewmodel.CartFragmentViewModel

/**
 * Created by ndt2101 on 11/4/2021.
 */
class CartViaShopAdapter(val model: CartFragmentViewModel, val shopPosition: Int, val lifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<CartViaShopAdapter.CartViaShopViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViaShopViewHolder {
        return CartViaShopViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CartViaShopViewHolder, position: Int) {
        holder.bind(model, shopPosition, position, lifecycleOwner)
    }

    override fun getItemCount(): Int {
        return model.listProductInCart.value?.get(shopPosition)?.value?.listProduct?.value?.size ?: 0
    }

    class CartViaShopViewHolder(val binding: ProductInCartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): CartViaShopViewHolder {
                return CartViaShopViewHolder(
                    ProductInCartItemBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )
            }
        }

        fun bind(model: CartFragmentViewModel, shopPosition: Int, position: Int, lifecycleOwner: LifecycleOwner) {
            model.listProductInCart.value!![shopPosition].value?.listProduct?.value!![position].observe(lifecycleOwner, {
                if (model.listProductInCart.value?.get(shopPosition)?.value?.listProduct?.value!![position].value != null) {
                    binding.productInCart = model.listProductInCart.value!![shopPosition].value?.listProduct?.value!![position].value
                    binding.lifecycleOwner = lifecycleOwner
                }
            })
        }
    }
}