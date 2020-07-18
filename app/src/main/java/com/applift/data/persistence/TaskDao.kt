package com.applift.data.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import com.applift.data.model.Task
import io.reactivex.Single

@Dao
interface TaskDao {

    @Insert
    fun insertTask(task: Task): Single<Long>

    @Update
    fun updateTask(task: Task): Single<Int>

    @Query("Select * from task WHERE project_id = :project_id")
    fun getAllTasks(project_id: Int): LiveData<List<Task>>
}