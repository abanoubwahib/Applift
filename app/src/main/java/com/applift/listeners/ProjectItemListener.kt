package com.applift.listeners

import com.applift.data.model.Project

interface ProjectItemListener {
    fun onItemSelected(project: Project)
}