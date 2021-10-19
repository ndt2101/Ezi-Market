package com.tuan2101.ezimarket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tuan2101.ezimarket.dataclasses.IntroPhoto
import com.tuan2101.ezimarket.databinding.IntroScreenBinding

class IntroAdapter(private val listIntroPhoto: List<IntroPhoto>) : RecyclerView.Adapter<IntroAdapter.IntroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {
        return IntroViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {
        holder.bind(listIntroPhoto, position)
    }

    override fun getItemCount(): Int {
        return listIntroPhoto.size
    }

     class IntroViewHolder(private val binding: IntroScreenBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup) : IntroViewHolder {
                val inflater: LayoutInflater = LayoutInflater.from(parent.context)
                return IntroViewHolder(IntroScreenBinding.inflate(inflater, parent, false))
            }
        }

        fun bind(listIntroPhoto: List<IntroPhoto>, position: Int) {
            binding.image.setImageResource(listIntroPhoto[position].src)
            binding.text.text = listIntroPhoto[position].text
        }
    }
}

