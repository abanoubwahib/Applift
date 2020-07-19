package com.applift.data.repository

import android.annotation.SuppressLint
import android.os.Build
import com.applift.data.model.Comment
import com.applift.data.model.Project
import com.applift.data.model.Task
import com.applift.data.local.LocalData
import com.applift.utils.IN_REVIEW
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class DataRepository @Inject
constructor(private val localData: LocalData) :
    DataRepositorySource {

    private var project: Project? = null
    private var task: Task? = null

    @ExperimentalCoroutinesApi
    override suspend fun insertProject(project_name: String): Flow<Long> {
        val project = Project(project_name)
        return localData.insertProject(project)
    }

    @ExperimentalCoroutinesApi
    override suspend fun getAllProjects(): Flow<List<Project>> {
        return localData.getAllProjects()
    }

    @SuppressLint("SimpleDateFormat")
    @ExperimentalCoroutinesApi
    override suspend fun insertTask(taskName: String, taskDescription: String): Flow<Long> {
        val date: String?
        date = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            current.format(formatter)
        } else {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
            sdf.format(Date())
        }

        return localData.insertTask(
            Task(
                title = taskName,
                description = taskDescription,
                createdDate = date,
                project_id = project?.id.toString()
            )
        )
    }

    @ExperimentalCoroutinesApi
    override suspend fun updateTask(task: Task): Flow<Long>? {
        return localData.insertTask(task)
    }

    @ExperimentalCoroutinesApi
    override suspend fun getAllTasks(): Flow<List<Task>>? {
        return project?.id?.let { localData.getAllTasks(it) }
    }

    @ExperimentalCoroutinesApi
    override suspend fun insertComment(commentStr: String): Flow<Long>? {
        return task?.id?.let {
            val comment = Comment(it, commentStr)
            return localData.insertComment(comment)
        }
    }

    @ExperimentalCoroutinesApi
    override suspend fun getAllComments(): Flow<List<Comment>>? {
        return task?.id?.let { localData.getAllComments(it) }
    }

    @ExperimentalCoroutinesApi
    override suspend fun updateTaskStatus(): Flow<Long>? {
        if (!task?.status.equals(IN_REVIEW)) {
            task?.status = IN_REVIEW
            return task?.let { localData.insertTask(it) }
        }
        return null
    }

    @ExperimentalCoroutinesApi
    override suspend fun getTaskById(): Flow<Task>? {
        return task?.id?.let { localData.getTaskById(it) }
    }

    override fun saveProject(project: Project) {
        this.project = project
    }

    override fun getSavedProjectTitle(): String? {
        return project?.name
    }

    override fun getSavedTask(): Task? {
        return task
    }

    override fun saveTask(task: Task) {
        this.task = task
    }
}