package com.applift.project

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.applift.FakeDataRepository
import com.applift.MainCoroutineRule
import com.applift.repository.DataRepositorySource
import com.applift.ui.dashboard.DashboardViewModel
import com.applift.ui.project.ProjectViewModel
import junit.framework.Assert
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ProjectViewModelTest {

    // Subject under test
    private lateinit var viewModel: ProjectViewModel

    // Use a fake repository to be injected into the viewmodel
    private lateinit var mDataRepo: DataRepositorySource

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        mDataRepo = FakeDataRepository()
        viewModel = ProjectViewModel(mDataRepo)
    }

    @Test
    fun onTaskAdd_Test() {
        runBlocking {
            viewModel.onAddTask("Task0","Description0")

            mDataRepo.getAllTasks()?.collect {
                assertEquals(it[0].title, "Task0")
            }
        }
    }

    @Test
    fun getTasks_Test() {
        runBlocking {
            mDataRepo.insertTask("Task0","Description0")
            mDataRepo.insertTask("Task1","Description1")
            mDataRepo.insertTask("Task2","Description2")
            mDataRepo.insertTask("Task3","Description3")

            mDataRepo.getAllTasks()?.collect{
                assertNotNull(it)
                assertNotEquals(it.size, 0)
            }
        }

    }
}