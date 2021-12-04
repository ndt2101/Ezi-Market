package com.tuan2101.ezimarket.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tuan2101.ezimarket.databinding.BillViaShopItemBinding
import com.tuan2101.ezimarket.dataclasses.Bill
import com.tuan2101.ezimarket.dataclasses.ProductInCart
import com.tuan2101.ezimarket.dataclasses.ProductViaShopInCart

/**
 * Created by ndt2101 on 11/28/2021.
 */
class ConfirmationAdapter(
    val bills: ArrayList<Bill>,
    val lifecycleOwner: LifecycleOwner, // dang nghi khong on
    val listener: BillClickListener
) : RecyclerView.Adapter<ConfirmationAdapter.ProductViaShopViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViaShopViewHolder {
        return ProductViaShopViewHolder.from(parent, lifecycleOwner)
    }

    override fun onBindViewHolder(holder: ProductViaShopViewHolder, position: Int) {
        holder.bind(bills[position], listener)
    }

    override fun getItemCount(): Int {
        return bills.size
    }

    class ProductViaShopViewHolder(
        val binding: BillViaShopItemBinding,
        val lifecycleOwner: LifecycleOwner,
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup, lifecycleOwner: LifecycleOwner): ProductViaShopViewHolder {
                return ProductViaShopViewHolder(
                    BillViaShopItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), lifecycleOwner
                )
            }
        }

        fun bind(bill: Bill, listener: BillClickListener) {
            val productViaShopInBillAdapter = ProductViaShopInBillAdapter(bill.listProduct as ArrayList<ProductInCart>)
            binding.bill = bill
            binding.lifecycleOwner = lifecycleOwner
            binding.listProduct.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
            binding.listProduct.adapter = productViaShopInBillAdapter
            binding.listener = listener
            binding.noteToSeller.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    bill.noteToSeller = p0.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }

            })
        }
    }

    class BillClickListener(val setShippingMethod: (bill: Bill) -> Unit) {
        fun onClickShippingMethod(bill: Bill) = setShippingMethod(bill)
    }
}