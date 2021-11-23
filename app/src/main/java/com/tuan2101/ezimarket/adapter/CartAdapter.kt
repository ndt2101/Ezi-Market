package com.tuan2101.ezimarket.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.*
import com.tuan2101.ezimarket.databinding.ProductViaShopInCartBinding
import com.tuan2101.ezimarket.dataclasses.ProductViaShopInCart
import com.tuan2101.ezimarket.viewmodel.CartFragmentViewModel
import java.util.concurrent.Executors

/**
 * Created by ndt2101 on 11/4/2021.
 */
class CartAdapter(
    val clickListener: ClickListener,
    val model: CartFragmentViewModel,
    val lifecycleOwner: LifecycleOwner,
    val productClickListener: CartViaShopAdapter.ClickListener
) : ListAdapter<ProductViaShopInCart, CartAdapter.ProductViaShopViewHolder>(
    AsyncDifferConfig.Builder(ProductViaShopInCartCallBack())
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
) {

    init {
        Log.i("re", "create")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViaShopViewHolder {
        return ProductViaShopViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ProductViaShopViewHolder, position: Int) {
        holder.bind(
            getItem(position),
            clickListener,
            model,
            position,
            lifecycleOwner,
            productClickListener
        )
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

            binding.productViaShopRcv.layoutManager =
                LinearLayoutManager(
                    binding.root.context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            binding.lifecycleOwner = lifecycleOwner
            binding.productViaShopInCart = data
            val clickListenerClone = ClickListener(
                clickListener.clickAllProductViaShop,
                clickListener.clickVisitShop, clickListener.clickSelectVoucher
            )
            binding.listener = clickListenerClone
            val cartViaShopAdapter =
                CartViaShopAdapter(data, model, position, lifecycleOwner, productClickListener)
            cartViaShopAdapter.submitList(data.listProduct!!)
            binding.productViaShopRcv.adapter = cartViaShopAdapter
        }
    }

    class ProductViaShopInCartCallBack : DiffUtil.ItemCallback<ProductViaShopInCart>() {
        override fun areItemsTheSame(
            oldItem: ProductViaShopInCart,
            newItem: ProductViaShopInCart
        ): Boolean {
            Log.i("so sanh item", (oldItem.shopId == newItem.shopId).toString())
            return oldItem.shopId == newItem.shopId
        }

        override fun areContentsTheSame(
            oldItem: ProductViaShopInCart,
            newItem: ProductViaShopInCart
        ): Boolean {
            Log.i("so sanh noi dung", "${oldItem.listProduct!!.size} == ${newItem.listProduct!!.size}")
            return false
        }
    }

    class ClickListener(
        val clickAllProductViaShop: (productViaShopInCart: ProductViaShopInCart) -> Unit,
        val clickVisitShop: (shopId: String) -> Unit,
        val clickSelectVoucher: (productViaShopInCart: ProductViaShopInCart) -> Unit
    ) {
        fun onSelectAllProductViaShop(productViaShopInCart: ProductViaShopInCart) = clickAllProductViaShop(productViaShopInCart)
        fun onClickVisitShop(shopId: String) = clickVisitShop(shopId)
        fun onClickSelectVoucher(productViaShopInCart: ProductViaShopInCart) = clickSelectVoucher(productViaShopInCart)
    }
}
