package com.applift.data.persistence

import androidx.room.*
import com.applift.data.model.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task): Long

    @Query("Select * from task WHERE project_id = :project_id")
    suspend fun getAllTasks(project_id: Int): List<Task>

    @Query("Select * from task WHERE id = :task_id Limit 1")
    suspend fun getTaskById(task_id: String): Task
}