package com.applift.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.applift.data.model.Project
import com.applift.databinding.ProjectItemBinding
import com.applift.listeners.ProjectItemListener
import com.applift.ui.dashboard.DashboardViewModel

class ProjectsAdapter(private val dashboardViewModel: DashboardViewModel,
                      private val projects: List<Project>) : RecyclerView.Adapter<ProjectsViewHolder>() {

    private val onItemClickListener: ProjectItemListener = object : ProjectItemListener {
        override fun onItemSelected(project: Project) {
            dashboardViewModel.handleProjectClicked(project)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsViewHolder {
        val itemBinding = ProjectItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProjectsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ProjectsViewHolder, position: Int) {
        holder.bind(projects[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return projects.size
    }
}

