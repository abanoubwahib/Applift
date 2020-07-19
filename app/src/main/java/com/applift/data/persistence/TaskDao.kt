package com.applift.data.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.applift.data.model.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task): Long

    @Query("Select * from task WHERE project_id = :project_id")
    suspend fun getAllTasks(project_id: Int): List<Task>

    @Query("UPDATE task SET status = :status WHERE id = :task_id")
    fun updateTask(status: String?, task_id: String): Int

    @Query("Select * from task WHERE id = :task_id Limit 1")
    suspend fun getTaskById(task_id: String): Task
}