package com.applift.ui.project.adapter

import androidx.recyclerview.widget.RecyclerView
import com.applift.data.model.Task
import com.applift.databinding.TaskItemBinding
import com.applift.listeners.TaskItemListener

class TaskViewHolder(private val itemBinding: TaskItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(task: Task, recyclerItemListener: TaskItemListener) {
        itemBinding.taskName.text = task.title
        itemBinding.rlTaskItem.setOnClickListener { recyclerItemListener.onItemSelected(task) }
    }
}