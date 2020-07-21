package com.applift.data.persistence

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class BaseDatabaseTest {

    private lateinit var mainDatabase: MainDatabase

    lateinit var projectDao: ProjectDao
    lateinit var taskDao: TaskDao
    lateinit var commentDao: CommentDao

    @Before
    fun init() {
        mainDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MainDatabase::class.java
        ).build()

        projectDao = mainDatabase.getProjectDao()
        taskDao = mainDatabase.getTaskDao()
        commentDao = mainDatabase.getCommentDao()
    }

    @After
    fun closeDatabase() {
        mainDatabase.close()
    }
}