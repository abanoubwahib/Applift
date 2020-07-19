package com.applift.data.persistence

import androidx.room.*
import com.applift.data.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert
    suspend fun insertTask(task: Task): Long

    @Update
    suspend fun updateTask(task: Task): Int

    @Query("Select * from task WHERE project_id = :project_id")
    suspend fun getAllTasks(project_id: Int): List<Task>
}