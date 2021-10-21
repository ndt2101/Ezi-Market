package com.tuan2101.ezimarket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tuan2101.ezimarket.databinding.AdvertisementItemBinding
import com.tuan2101.ezimarket.dataclasses.AdvertisementPhoto

class AdvertisementAdapter(val listImg: List<AdvertisementPhoto>) :
    RecyclerView.Adapter<AdvertisementAdapter.AdvertisementViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertisementViewHolder {
        return AdvertisementViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: AdvertisementViewHolder, position: Int) {
        holder.bind(listImg[position])
    }

    override fun getItemCount(): Int {
        return listImg.size
    }

    class AdvertisementViewHolder(val binding: AdvertisementItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(advertisementPhoto: AdvertisementPhoto) {
            Glide.with(binding.root.context).load(advertisementPhoto.img).into(binding.adImg)
        }

        companion object {
            fun from(parent: ViewGroup): AdvertisementViewHolder {
                return AdvertisementViewHolder(
                    AdvertisementItemBinding.inflate(
                        LayoutInflater.from(
                        parent.context
                    ), parent, false)
                )
            }
        }
    }

}

sealed class DataItem() {
    class Item(val advertisementPhoto: AdvertisementPhoto): DataItem()
    class Footer() : DataItem()
}