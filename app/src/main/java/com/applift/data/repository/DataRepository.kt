package com.applift.data.repository

import com.applift.data.Resource
import com.applift.data.model.Comment
import com.applift.data.model.Project
import com.applift.data.model.Task
import com.applift.data.local.LocalData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataRepository @Inject
constructor(private val localData: LocalData) :
    DataRepositorySource {

    override suspend fun insertProject(project: Project): Flow<Resource<Long>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllProjects(): Flow<Resource<List<Project>>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertTask(task: Task): Flow<Resource<Long>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateTask(task: Task): Flow<Resource<Long>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllTasks(project_id: Int): Flow<Resource<List<Task>>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertComment(comment: Comment): Flow<Resource<Long>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllComments(task_id: Int): Flow<Resource<List<Task>>> {
        TODO("Not yet implemented")
    }
}