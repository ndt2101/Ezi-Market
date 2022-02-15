package com.tuan2101.ezimarket.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tuan2101.ezimarket.databinding.CommentItemBinding
import com.tuan2101.ezimarket.dataclasses.Comment

/**
 * Created by ndt2101 on 11/1/2021.
 */
class CommentAdapter(val listener: CommentListener) : ListAdapter<Comment, CommentAdapter.CommentViewHolder>(CommentCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        return holder.bind(getItem(position), listener)
    }

    class CommentViewHolder(val binding: CommentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): CommentViewHolder {
                return CommentViewHolder(
                    CommentItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

        fun bind(comment: Comment, listener: CommentListener) {
            binding.comment = comment
            binding.shortCommentContent.setOnClickListener {
                binding.fullCommentContent.visibility = View.VISIBLE
                it.visibility = View.GONE
            }
            binding.listener = listener
            binding.fullCommentContent.setOnClickListener {
                binding.shortCommentContent.visibility = View.VISIBLE
                it.visibility  = View.GONE
            }
        }
    }

    class CommentCallBack : DiffUtil.ItemCallback<Comment>() {
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem == newItem
        }

    }

    class CommentListener(val clickUser: (userId: String) -> Unit) {
        fun onClickUser(userId: String) = clickUser(userId)
    }
}