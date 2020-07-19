package com.applift.ui.task.adapter

import androidx.recyclerview.widget.RecyclerView
import com.applift.data.model.Comment
import com.applift.databinding.CommentItemBinding

class CommentViewHolder(private val itemBinding: CommentItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(comment: Comment) {
        itemBinding.comment.setText(comment.comment_str)
    }
}