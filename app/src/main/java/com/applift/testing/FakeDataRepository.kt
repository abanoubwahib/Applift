package com.applift

import androidx.annotation.VisibleForTesting
import com.applift.data.model.Comment
import com.applift.data.model.Project
import com.applift.data.model.Task
import com.applift.repository.DataRepositorySource
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
        counter_projects += 1
        return flow {

            emit(counter_projects.toLong())
        }.flowOn(Dispatchers.IO)
    }

    @ExperimentalCoroutinesApi
    override suspend fun insertTask(taskName: String, taskDescription: String): Flow<Long> {
        counter_tasks += 1
        return flow {
            emit(counter_tasks.toLong())
        }.flowOn(Dispatchers.IO)
    }

    @ExperimentalCoroutinesApi
    override suspend fun insertComment(commentStr: String): Flow<Long>? {
        counter_comments += 1
        return flow {
            emit(counter_comments.toLong())
        }.flowOn(Dispatchers.IO)
    }

    @ExperimentalCoroutinesApi
    override suspend fun getAllProjects(): Flow<List<Project>> {
        val projects = mutableListOf<Project>()
        for (i in 0 until counter_projects) {
            projects.add(Project("Project$i"))
        }

        return flow {
            emit(projects)
        }.flowOn(Dispatchers.IO)
    }

    @ExperimentalCoroutinesApi
    override suspend fun getAllTasks(): Flow<List<Task>>? {
        val tasks = mutableListOf<Task>()
        for (i in 0 until counter_tasks) {
            tasks.add(Task("Task$i", "Description1", "", "1"))
        }
        return flow {
            emit(tasks)
        }.flowOn(Dispatchers.IO)
    }

    @ExperimentalCoroutinesApi
    override suspend fun getAllComments(): Flow<List<Comment>>? {
        val comments = mutableListOf<Comment>()

        for (i in 0 until counter_comments) {
            comments.add(Comment(1, "Comment$i"))
        }
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
            task.status = status
            emit(1)
        }.flowOn(Dispatchers.IO)
    }

    @ExperimentalCoroutinesApi
    override suspend fun getTaskById(): Flow<Task>? {
        return flow {
            emit(task)
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

    companion object {
        lateinit var project: Project
        lateinit var task: Task
        var counter_projects: Int = 0
        var counter_tasks: Int = 0
        var counter_comments: Int = 0
    }
}