package com.tuan2101.ezimarket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tuan2101.ezimarket.databinding.VoucherItemBinding
import com.tuan2101.ezimarket.dataclasses.PostVoucher
import com.tuan2101.ezimarket.dataclasses.Voucher

/**
 * Created by ndt2101 on 11/14/2021.
 */
class VoucherAdapter(val lifecycleOwner: LifecycleOwner, val onClickListener: OnClickListener): ListAdapter<PostVoucher, VoucherAdapter.VoucherViewHolder>(VoucherCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoucherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return VoucherViewHolder.from(parent, layoutInflater)
    }

    override fun onBindViewHolder(holder: VoucherViewHolder, position: Int) {
        holder.bind(getItem(position), lifecycleOwner, onClickListener)
    }

    class VoucherViewHolder(val binding: VoucherItemBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup, inflater: LayoutInflater) : VoucherViewHolder {
                return VoucherViewHolder(VoucherItemBinding.inflate(inflater, parent, false))
            }
        }

        fun bind(voucher: PostVoucher, lifecycleOwner: LifecycleOwner, onClickListener: OnClickListener) {
            binding.voucher = voucher
            binding.lifecycleOwner = lifecycleOwner
            binding.listener = onClickListener
        }
    }

    class VoucherCallBack(): DiffUtil.ItemCallback<PostVoucher>() {
        override fun areItemsTheSame(oldItem: PostVoucher, newItem: PostVoucher): Boolean {
            return oldItem.voucherId == newItem.voucherId
        }

        override fun areContentsTheSame(oldItem: PostVoucher, newItem: PostVoucher): Boolean {
            return oldItem == newItem
        }

    }

    class OnClickListener(val clickSetVoucher: (voucher: PostVoucher) -> Unit) {
        fun onClickSetVoucher(voucher: PostVoucher) = clickSetVoucher(voucher)
    }
}