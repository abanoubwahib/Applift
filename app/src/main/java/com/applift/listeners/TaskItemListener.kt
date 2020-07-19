package com.applift.listeners

import com.applift.data.model.Task

interface TaskItemListener {
    fun onItemSelected(task: Task)
}