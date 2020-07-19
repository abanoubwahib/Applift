package com.applift.data.local

import com.applift.data.model.Comment
import com.applift.data.model.Project
import com.applift.data.model.Task
import com.applift.data.persistence.CommentDao
import com.applift.data.persistence.ProjectDao
import com.applift.data.persistence.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlinx.coroutines.flow.flow as flow

class LocalData @Inject constructor(
    val mProjectDao: ProjectDao,
    val mTaskDao: TaskDao,
    val mCommentDao: CommentDao
) {

    @ExperimentalCoroutinesApi
    suspend fun insertProject(project: Project): Flow<Long> {
        return flow {
            emit(mProjectDao.insertProject(project))
        }.flowOn(Dispatchers.IO)
    }

    @ExperimentalCoroutinesApi
    suspend fun getAllProjects(): Flow<List<Project>> {
        return flow {
            emit(mProjectDao.getAllProjects())
        }.flowOn(Dispatchers.IO)
    }

    @ExperimentalCoroutinesApi
    suspend fun insertTask(task: Task): Flow<Long> {
        return flow {
            emit(mTaskDao.insertTask(task))
        }.flowOn(Dispatchers.IO)
    }

    @ExperimentalCoroutinesApi
    suspend fun updateTask(task: Task): Flow<Int> {
        return flow {
            emit(mTaskDao.updateTask(task))
        }.flowOn(Dispatchers.IO)
    }

    @ExperimentalCoroutinesApi
    suspend fun getAllTasks(project_id: Int): Flow<List<Task>> {
        return flow {
            emit(mTaskDao.getAllTasks(project_id))
        }.flowOn(Dispatchers.IO)
    }

    @ExperimentalCoroutinesApi
    suspend fun insertComment(comment: Comment): Flow<Long> {
        return flow {
            emit(mCommentDao.insertComment(comment))
        }.flowOn(Dispatchers.IO)
    }

    @ExperimentalCoroutinesApi
    suspend fun getAllComments(task_id: Int): Flow<List<Comment>> {
        return flow {
            emit(mCommentDao.getAllComments(task_id))
        }.flowOn(Dispatchers.IO)
    }
}