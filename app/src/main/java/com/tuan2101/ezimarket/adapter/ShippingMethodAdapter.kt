package com.tuan2101.ezimarket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.tuan2101.ezimarket.databinding.ShippingMethodItemBinding
import com.tuan2101.ezimarket.dataclasses.ShippingMethod

/**
 * Created by ndt2101 on 11/29/2021.
 */
class ShippingMethodAdapter(val methodList: List<ShippingMethod>, val lifecycleOwner: LifecycleOwner, val listener: ShippingMethodListener) :
    RecyclerView.Adapter<ShippingMethodAdapter.ShippingMethodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShippingMethodViewHolder {
        return ShippingMethodViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ShippingMethodViewHolder, position: Int) {
        holder.bind(methodList[position], lifecycleOwner, listener)
    }

    override fun getItemCount(): Int {
        return methodList.size
    }

    class ShippingMethodViewHolder(val binding: ShippingMethodItemBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ShippingMethodViewHolder {
                return ShippingMethodViewHolder(
                    ShippingMethodItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

        fun bind(shippingMethod: ShippingMethod, lifecycleOwner: LifecycleOwner, listener: ShippingMethodListener) {
            binding.shippingMethod = shippingMethod
            binding.lifecycleOwner = lifecycleOwner
            binding.listener = listener
        }
    }

    class ShippingMethodListener(val clickMethod: (shippingMethod: ShippingMethod) -> Unit) {
        fun onClickShippingMethod(shippingMethod: ShippingMethod) = clickMethod(shippingMethod)
    }

}