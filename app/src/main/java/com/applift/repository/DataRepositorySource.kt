package com.applift.repository

import com.applift.data.model.Comment
import com.applift.data.model.Project
import com.applift.data.model.Task
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {

    suspend fun insertProject(project_name: String): Flow<Long>

    suspend fun insertTask(taskName: String, taskDescription: String): Flow<Long>

    suspend fun insertComment(commentStr: String): Flow<Long>?

    suspend fun getAllProjects(): Flow<List<Project>>

    suspend fun getAllTasks(): Flow<List<Task>>?

    suspend fun getAllComments(): Flow<List<Comment>>?

    suspend fun updateTask(task: Task): Flow<Long>?

    suspend fun updateTaskStatus(status: String): Flow<Int>?

    suspend fun getTaskById(): Flow<Task>?

    fun saveProject(project: Project)

    fun saveTask(task: Task)

    fun getSavedProjectTitle(): String?

    fun getSavedTask(): Task?
}