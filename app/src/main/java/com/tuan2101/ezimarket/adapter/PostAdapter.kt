package com.tuan2101.ezimarket.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.databinding.PostItemBinding
import com.tuan2101.ezimarket.dataclasses.Comment
import com.tuan2101.ezimarket.dataclasses.Post
import com.tuan2101.ezimarket.outsidefragment.HostFragmentDirections

/**
 * Created by ndt2101 on 10/31/2021.
 */
class PostAdapter(val postList: List<Post>, val listener: OnPostClickListener) : RecyclerView.Adapter<PostAdapter.PostViewHolder>(){
    class PostViewHolder(val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post, listener: OnPostClickListener) {
            if (post.postContentImage.isNullOrEmpty()) {
                binding.postImg.visibility = View.GONE
            }
            if (post.postVoucher == null) {
                binding.product.visibility = View.GONE
            }
            if (post.product == null) {
                binding.voucherArea.visibility = View.GONE
            }
            binding.shortDescription.setOnClickListener {
                binding.fullDescription.visibility = View.VISIBLE
                it.visibility = View.GONE
            }

            binding.fullDescription.setOnClickListener {
                binding.shortDescription.visibility = View.VISIBLE
                it.visibility = View.GONE
            }

            binding.postImg.setOnClickListener {
                it.findNavController().navigate(HostFragmentDirections.actionHostFragmentToImageDetailFragment(post.postContentImage!!))
            }
            binding.post = post
            binding.listener = listener
        }

        companion object {
            fun from(parent: ViewGroup) : PostViewHolder {
                return PostViewHolder(PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(postList[position], listener)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    class OnPostClickListener(val clickComment: (post: Post) -> Unit, val clickUser: (userId: String) -> Unit) {
        fun onClickComment(post: Post) = clickComment(post)
        fun onClickUser(userId: String) = clickUser(userId)
    }
}