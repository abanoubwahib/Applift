package com.applift.task

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.applift.FakeDataRepository
import com.applift.MainCoroutineRule
import com.applift.repository.DataRepositorySource
import com.applift.ui.project.ProjectViewModel
import com.applift.ui.task.TaskViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

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
}