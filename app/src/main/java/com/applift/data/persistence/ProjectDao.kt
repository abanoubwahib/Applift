package com.applift.data.persistence

import androidx.room.*
import com.applift.data.model.Project

@Dao
interface ProjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: Project): Long

    @Query("Select * from project")
    suspend fun getAllProjects(): List<Project>
}