package com.applift.data.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import com.applift.data.model.Project
import io.reactivex.Single

@Dao
interface ProjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProject(project: Project): Single<Long>

    @Query("Select * from project")
    fun getAllProjects(): LiveData<List<Project>>
}