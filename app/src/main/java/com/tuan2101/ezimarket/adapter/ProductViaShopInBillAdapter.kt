package com.tuan2101.ezimarket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tuan2101.ezimarket.databinding.ProductViaShopInBillBinding
import com.tuan2101.ezimarket.dataclasses.ProductInCart

/**
 * Created by ndt2101 on 11/28/2021.
 */
class ProductViaShopInBillAdapter(val listProduct: ArrayList<ProductInCart>) :
    RecyclerView.Adapter<ProductViaShopInBillAdapter.ProductViaShopInBillViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViaShopInBillViewHolder {
        return ProductViaShopInBillViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ProductViaShopInBillViewHolder, position: Int) {
        holder.bind(listProduct[position])
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    class ProductViaShopInBillViewHolder(val binding: ProductViaShopInBillBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ProductViaShopInBillViewHolder {
                return ProductViaShopInBillViewHolder(
                    ProductViaShopInBillBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

        fun bind(product: ProductInCart) {
            binding.productInCart = product
        }
    }
}