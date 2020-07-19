package com.applift.data.repository

import com.applift.data.model.Comment
import com.applift.data.model.Project
import com.applift.data.model.Task
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {

    suspend fun insertProject(project_name: String): Flow<Long>
    suspend fun getAllProjects(): Flow<List<Project>>

    suspend fun insertTask(taskName: String, taskDescription: String): Flow<Long>
    suspend fun updateTask(task: Task): Flow<Int>
    suspend fun getAllTasks(): Flow<List<Task>>?

    suspend fun insertComment(comment: Comment): Flow<Long>
    suspend fun getAllComments(): Flow<List<Comment>>?

    fun saveProject(project: Project)

    fun saveTask(task: Task)

    fun getTaskTitle(): String?
}