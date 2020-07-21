package com.applift.data.persistence

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.applift.data.model.Project
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ProjectDaoTest : BaseDatabaseTest() {

    @Test
    fun insertProjectTest() {
        runBlocking {
            val project = Project("Project1")

            projectDao.insertProject(project)
            projectDao.insertProject(project)
            projectDao.insertProject(project)
            projectDao.insertProject(project)
            projectDao.insertProject(project)

            val list = projectDao.getAllProjects()

            Assert.assertEquals(5, list.size)
        }
    }
}