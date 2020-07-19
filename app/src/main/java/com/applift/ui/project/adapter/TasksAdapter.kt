package com.applift.ui.project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.applift.data.model.Task
import com.applift.databinding.TaskItemBinding
import com.applift.listeners.TaskItemListener
import com.applift.ui.project.ProjectViewModel

class TasksAdapter(private val projectViewModel: ProjectViewModel,
                   private val tasks: List<Task>) : RecyclerView.Adapter<TaskViewHolder>() {

    private val onItemClickListener: TaskItemListener = object : TaskItemListener {
        override fun onItemSelected(task: Task) {
            projectViewModel.handleTaskClicked(task)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemBinding = TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}