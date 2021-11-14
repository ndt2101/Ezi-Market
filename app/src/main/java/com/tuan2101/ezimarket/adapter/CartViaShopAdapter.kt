package com.tuan2101.ezimarket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tuan2101.ezimarket.databinding.ProductInCartItemBinding
import com.tuan2101.ezimarket.dataclasses.ProductInCart
import com.tuan2101.ezimarket.dataclasses.ProductViaShopInCart
import com.tuan2101.ezimarket.viewmodel.CartFragmentViewModel

/**
 * Created by ndt2101 on 11/4/2021.
 */
class CartViaShopAdapter(
    val productViaShopInCart: ProductViaShopInCart,
    val model: CartFragmentViewModel,
    val shopPosition: Int,
    val lifecycleOwner: LifecycleOwner,
    val clickListener: ClickListener
) : ListAdapter<ProductInCart, CartViaShopAdapter.CartViaShopViewHolder>(CartViaShopCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViaShopViewHolder {
        return CartViaShopViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CartViaShopViewHolder, position: Int) {
        holder.bind(productViaShopInCart, model, shopPosition, position, lifecycleOwner, clickListener, getItem(position))
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

        fun bind(
            productViaShopInCart: ProductViaShopInCart,
            model: CartFragmentViewModel,
            shopPosition: Int,
            position: Int,
            lifecycleOwner: LifecycleOwner,
            clickListener: ClickListener,
            data: ProductInCart
        ) {
//            model.listProductInCart.value!![shopPosition].listProduct?.observe(
//                lifecycleOwner,
//                {
                    binding.productInCart = data
                    binding.lifecycleOwner = lifecycleOwner
                    binding.listener = clickListener
                    binding.productViaShopInCart = productViaShopInCart
//                })
        }
    }

    class CartViaShopCallBack() : DiffUtil.ItemCallback<ProductInCart>() {
        override fun areItemsTheSame(oldItem: ProductInCart, newItem: ProductInCart): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductInCart, newItem: ProductInCart): Boolean {
            return oldItem == newItem
        }

    }

    class ClickListener(
        val clickToPay: (productInCart: ProductInCart, productViaShopInCart: ProductViaShopInCart) -> Unit,
        val clickToVisitProductDetail: (productInCart: ProductInCart) -> Unit,
        val clickToBuyMore: (productInCart: ProductInCart, productViaShopInCart: ProductViaShopInCart) -> Unit,
        val clickToBuyLess: (productInCart: ProductInCart, productViaShopInCart: ProductViaShopInCart) -> Unit,
        val clickToDeleteProduct: (productInCart: ProductInCart, productViaShopInCart: ProductViaShopInCart) -> Unit
    ) {

        fun onClickToPay(productInCart: ProductInCart, productViaShopInCart: ProductViaShopInCart) =
            clickToPay(productInCart, productViaShopInCart)
        fun onClickToVisitProductDetail(productInCart: ProductInCart) =
            clickToVisitProductDetail(productInCart)

        fun onClickToBuyMore(productInCart: ProductInCart, productViaShopInCart: ProductViaShopInCart) =
            clickToBuyMore(productInCart, productViaShopInCart)

        fun onClickToBuyLess(productInCart: ProductInCart, productViaShopInCart: ProductViaShopInCart) =
            clickToBuyLess(productInCart, productViaShopInCart)

        fun onClickToDeleteProduct(productInCart: ProductInCart, productViaShopInCart: ProductViaShopInCart) =
            clickToDeleteProduct(productInCart, productViaShopInCart)
    }

    // TODO: Thay tất cả đối số của các hàm trong clickListener thành các đối tượng
}