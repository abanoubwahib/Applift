package com.applift.task

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.applift.FakeDataRepository
import com.applift.MainCoroutineRule
import com.applift.repository.DataRepositorySource
import com.applift.ui.project.ProjectViewModel
import com.applift.ui.task.TaskViewModel
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TaskViewModelTest {

    // Subject under test
    private lateinit var viewModel: TaskViewModel

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
        viewModel = TaskViewModel(mDataRepo)
    }

    @Test
    fun addComment_Test() {
        runBlocking {
            mDataRepo.insertComment("Comment0")

            mDataRepo.getAllComments()?.collect {
                assertNotNull(it)
                Assert.assertEquals(it[0].comment_str, "Comment0")
            }
        }
    }

    @Test
    fun getAllComments_Test() {
        runBlocking {
            mDataRepo.insertComment("Comment0")
            mDataRepo.insertComment("Comment1")
            mDataRepo.insertComment("Comment2")
            mDataRepo.insertComment("Comment3")

            mDataRepo.getAllComments()?.collect {
                Assert.assertNotNull(it)
                Assert.assertNotEquals(it.size, 0)
            }
        }
    }
}