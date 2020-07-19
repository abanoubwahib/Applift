package com.applift.listeners

interface AddTaskCallback {
    fun onTaskAdded(task_name: String, task_description: String)
}