package com.tuan2101.ezimarket.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
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
class CartAdapter(
    val clickListener: ClickListener,
    val model: CartFragmentViewModel,
    val lifecycleOwner: LifecycleOwner,
    val productClickListener: CartViaShopAdapter.ClickListener
) : ListAdapter<ProductViaShopInCart , CartAdapter.ProductViaShopViewHolder>(ProductViaShopInCartCallBack()) {

    init {
        Log.i("re", "create")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViaShopViewHolder {
        return ProductViaShopViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ProductViaShopViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener, model, position, lifecycleOwner, productClickListener)
    }

    class ProductViaShopViewHolder(val binding: ProductViaShopInCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
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

        fun bind(
            data: ProductViaShopInCart,
            clickListener: ClickListener,
            model: CartFragmentViewModel,
            position: Int,
            lifecycleOwner: LifecycleOwner,
            productClickListener: CartViaShopAdapter.ClickListener
        ) {

            model.listProductInCart.value!![position].listProduct?.observe(
                lifecycleOwner,
                {
                    binding.productViaShopRcv.layoutManager =
                        LinearLayoutManager(
                            binding.root.context,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                    binding.lifecycleOwner = lifecycleOwner
                    binding.productViaShopInCart = data
                    binding.listener = clickListener
                    val cartViaShopAdapter =
                        CartViaShopAdapter(model, position, lifecycleOwner, productClickListener)
                    binding.productViaShopRcv.adapter = cartViaShopAdapter
                })
        }
    }

    class ProductViaShopInCartCallBack() : DiffUtil.ItemCallback<ProductViaShopInCart>() {
        override fun areItemsTheSame(
            oldItem: ProductViaShopInCart,
            newItem: ProductViaShopInCart
        ): Boolean {
            return oldItem.shopId == newItem.shopId
        }

        override fun areContentsTheSame(
            oldItem: ProductViaShopInCart,
            newItem: ProductViaShopInCart
        ): Boolean {
            return oldItem == newItem
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