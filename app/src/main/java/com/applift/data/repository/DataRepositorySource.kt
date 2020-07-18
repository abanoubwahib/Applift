package com.applift.data.repository

import com.applift.data.Resource
import com.applift.data.model.Comment
import com.applift.data.model.Project
import com.applift.data.model.Task
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {

    suspend fun insertProject(project: Project): Flow<Resource<Long>>
    suspend fun getAllProjects(): Flow<Resource<List<Project>>>

    suspend fun insertTask(task: Task): Flow<Resource<Long>>
    suspend fun updateTask(task: Task): Flow<Resource<Long>>
    suspend fun getAllTasks(project_id: Int): Flow<Resource<List<Task>>>

    suspend fun insertComment(comment: Comment): Flow<Resource<Long>>
    suspend fun getAllComments(task_id: Int): Flow<Resource<List<Task>>>

    fun saveProject(project: Project)

    fun getProject(): Project?
}