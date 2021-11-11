package com.tuan2101.ezimarket.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.tuan2101.ezimarket.databinding.ProductInCartItemBinding
import com.tuan2101.ezimarket.dataclasses.ProductInCart
import com.tuan2101.ezimarket.viewmodel.CartFragmentViewModel

/**
 * Created by ndt2101 on 11/4/2021.
 */
class CartViaShopAdapter(
    val model: CartFragmentViewModel,
    val shopPosition: Int,
    val lifecycleOwner: LifecycleOwner,
    val clickListener: ClickListener
) : RecyclerView.Adapter<CartViaShopAdapter.CartViaShopViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViaShopViewHolder {
        return CartViaShopViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CartViaShopViewHolder, position: Int) {
        holder.bind(model, shopPosition, position, lifecycleOwner, clickListener)
    }

    override fun getItemCount(): Int {
        return model.listProductInCart.value?.get(shopPosition)?.listProduct?.value?.size
            ?: 0
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
            model: CartFragmentViewModel,
            shopPosition: Int,
            position: Int,
            lifecycleOwner: LifecycleOwner,
            clickListener: ClickListener
        ) {
//            model.listProductInCart.value!![shopPosition].listProduct?.observe(
//                lifecycleOwner,
//                {
                    if (model.listProductInCart.value?.get(shopPosition)?.listProduct?.value!![position].value != null) {
                        binding.productInCart =
                            model.listProductInCart.value!![shopPosition].listProduct?.value!![position].value
                        binding.lifecycleOwner = lifecycleOwner
                        val clickListenerClone = ClickListener(position,shopPosition,clickListener.clickToPay,clickListener.clickToVisitProductDetail,
                        clickListener.clickToBuyMore, clickListener.clickToBuyLess, clickListener.clickToDeleteProduct)
                        binding.listener = clickListenerClone
//                        Log.i("chay vao day", "chay vao day")
                    }
//                })
        }
    }

    class ClickListener(
        var position: Int,
        var shopPosition: Int,
        val clickToPay: (position: Int, shopPosition: Int) -> Unit,
        val clickToVisitProductDetail: (productInCart: ProductInCart) -> Unit,
        val clickToBuyMore: (position: Int, shopPosition: Int) -> Unit,
        val clickToBuyLess: (position: Int, shopPosition: Int) -> Unit,
        val clickToDeleteProduct: (position: Int, shopPosition: Int) -> Unit
    ) {

        fun onClickToPay(position: Int, shopPosition: Int) = clickToPay(position, shopPosition)
        fun onClickToVisitProductDetail(productInCart: ProductInCart) =
            clickToVisitProductDetail(productInCart)

        fun onClickToBuyMore(position: Int, shopPosition: Int) =
            clickToBuyMore(position, shopPosition)

        fun onClickToBuyLess(position: Int, shopPosition: Int) =
            clickToBuyLess(position, shopPosition)

        fun onClickToDeleteProduct(position: Int, shopPosition: Int) =
            clickToDeleteProduct(position, shopPosition)
    }
}