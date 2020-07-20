package com.applift

import androidx.annotation.VisibleForTesting
import com.applift.data.model.Comment
import com.applift.data.model.Project
import com.applift.data.model.Task
import com.applift.data.repository.DataRepositorySource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@VisibleForTesting
@Singleton
class FakeDataRepository @Inject constructor() : DataRepositorySource {



    @ExperimentalCoroutinesApi
    override suspend fun insertProject(project_name: String): Flow<Long> {
        return flow {
            emit(1L)
        }.flowOn(Dispatchers.IO)
    }

    @ExperimentalCoroutinesApi
    override suspend fun insertTask(taskName: String, taskDescription: String): Flow<Long> {
        return flow {
            emit(1L)
        }.flowOn(Dispatchers.IO)
    }

    @ExperimentalCoroutinesApi
    override suspend fun insertComment(commentStr: String): Flow<Long>? {
        return flow {
            emit(1L)
        }.flowOn(Dispatchers.IO)
    }

    @ExperimentalCoroutinesApi
    override suspend fun getAllProjects(): Flow<List<Project>> {
        val projects = mutableListOf<Project>()

        projects.add(Project("Project1"))

        return flow {
            emit(projects)
        }.flowOn(Dispatchers.IO)
    }

    @ExperimentalCoroutinesApi
    override suspend fun getAllTasks(): Flow<List<Task>>? {
        val tasks = mutableListOf<Task>()

        tasks.add(Task("Task1", "Description1", "", "1"))

        return flow {
            emit(tasks)
        }.flowOn(Dispatchers.IO)
    }

    @ExperimentalCoroutinesApi
    override suspend fun getAllComments(): Flow<List<Comment>>? {
        val comments = mutableListOf<Comment>()

        comments.add(Comment(1, "Comment1"))
        comments.add(Comment(1, "Comment2"))

        return flow {
            emit(comments)
        }.flowOn(Dispatchers.IO)
    }

    @ExperimentalCoroutinesApi
    override suspend fun updateTask(task: Task): Flow<Long>? {
        return flow {
            emit(1L)
        }.flowOn(Dispatchers.IO)
    }

    @ExperimentalCoroutinesApi
    override suspend fun updateTaskStatus(status: String): Flow<Int>? {
        return flow {
            emit(1)
        }.flowOn(Dispatchers.IO)
    }

    @ExperimentalCoroutinesApi
    override suspend fun getTaskById(): Flow<Task>? {
        return flow {
            emit(Task("Task1", "Description1", "", "1"))
        }.flowOn(Dispatchers.IO)
    }

    override fun saveProject(project: Project) {
        FakeDataRepository.project = project
    }

    override fun saveTask(task: Task) {
        FakeDataRepository.task = task
    }

    override fun getSavedProjectTitle(): String? {
        return project.name
    }

    override fun getSavedTask(): Task? {
        return task
    }

    companion object{
        lateinit var project: Project
        lateinit var task: Task
    }
}