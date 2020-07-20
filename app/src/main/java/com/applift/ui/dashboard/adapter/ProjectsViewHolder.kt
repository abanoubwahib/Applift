package com.applift.ui.dashboard.adapter

import androidx.recyclerview.widget.RecyclerView
import com.applift.data.model.Project
import com.applift.databinding.ProjectItemBinding
import com.applift.listeners.ProjectItemListener

class ProjectsViewHolder(private val itemBinding: ProjectItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(project: Project, recyclerItemListener: ProjectItemListener) {
        itemBinding.projectName.text = project.name
        itemBinding.rlProjectItem.setOnClickListener { recyclerItemListener.onItemSelected(project) }
    }
}