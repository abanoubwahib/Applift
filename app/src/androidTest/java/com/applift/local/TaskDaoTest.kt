package com.applift.data.persistence

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.applift.data.model.Project
import com.applift.data.model.Task
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class TaskDaoTest : BaseDatabaseTest() {

    @Test
    fun insertProjectTest() {
        runBlocking {
            val task = Task("Task1", "Description1", "20-7-2020", 1.toString())
            val task2 = Task("Task1", "Description1", "20-7-2020", 1.toString())
            val task3 = Task("Task1", "Description1", "20-7-2020", 1.toString())
            val task4 = Task("Task1", "Description1", "20-7-2020",1.toString())

            taskDao.insertTask(task)
            taskDao.insertTask(task2)
            taskDao.insertTask(task3)
            taskDao.insertTask(task4)

            val list = taskDao.getAllTasks(1)

            Assert.assertEquals(4, list.size)
        }
    }
}