package com.applift.listeners

import com.applift.data.model.Task

interface EditTaskCallback {
    fun onTaskUpdated(task: Task)
}