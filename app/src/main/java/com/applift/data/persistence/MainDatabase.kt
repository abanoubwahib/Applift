package com.applift.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.applift.data.model.Comment
import com.applift.data.model.Project
import com.applift.data.model.Task

@Database(
    entities = [Project::class, Task::class, Comment::class],
    version = 1,
    exportSchema = false
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun getProjectDao(): ProjectDao
    abstract fun getTaskDao(): TaskDao
    abstract fun getCommentDao(): CommentDao
}