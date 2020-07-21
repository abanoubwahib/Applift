package com.applift.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.applift.FakeDataRepository
import com.applift.MainCoroutineRule
import com.applift.repository.DataRepositorySource
import com.applift.ui.dashboard.DashboardViewModel
import com.google.common.truth.Truth.assertThat
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DashboardViewModelTest {

    // Subject under test
    private lateinit var viewModel: DashboardViewModel

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
        viewModel = DashboardViewModel(mDataRepo)
    }

    @Test
    fun onProjectAdd_Test() {
        runBlocking {
            viewModel.onProjectAdd("Project0")
            mDataRepo.getAllProjects().collect {
                assertEquals(it[0].name, "Project0")
            }
        }
    }

    @Test
    fun getProjectsTest() {
        runBlocking {
            mDataRepo.insertProject("Project1")
            mDataRepo.insertProject("Project2")
            mDataRepo.insertProject("Project3")
            mDataRepo.getAllProjects().collect {
                assertNotNull(it)
                assertEquals(it.size, 3)
            }
        }
    }
}