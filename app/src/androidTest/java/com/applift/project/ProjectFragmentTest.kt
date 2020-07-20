package com.applift.project

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.applift.FakeDataRepository
import com.applift.R
import com.applift.data.model.Project
import com.applift.data.repository.DataRepositorySource
import com.applift.ui.project.ProjectFragment
import com.applift.ui.project.adapter.TaskViewHolder
import com.applift.utils.testing.AppTest
import com.applift.utils.testing.DaggerTestAppComponent
import com.google.common.truth.Truth.assertThat
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@LargeTest
@ExperimentalCoroutinesApi
class ProjectFragmentTest {

    lateinit var mDataRepository: DataRepositorySource

    @Before
    fun init() {
        DaggerTestAppComponent.builder().application(AppTest()).build()
        mDataRepository = FakeDataRepository()
    }

    @Test
    fun addTaskTest() {

        val project = Project("project_name")
        project.id = 1

        mDataRepository.saveProject(project)

        runBlocking {
            val navController = TestNavHostController(
                ApplicationProvider.getApplicationContext()
            ).apply {
                setGraph(R.navigation.home)
                setCurrentDestination(R.id.projectFragment)
            }

            val projectScenario = launchFragmentInContainer<ProjectFragment>()

            projectScenario.onFragment { fragment ->
                Navigation.setViewNavController(fragment.requireView(), navController)
            }

            onView(withId(R.id.fab)).perform(click())

            onView(withId(R.id.title)).perform(typeText("Task1"))
            onView(withId(R.id.description)).perform(typeText("Description1"))

            onView(withId(R.id.add)).perform(click())

            onView(withId(R.id.rvTasks)).perform(
                RecyclerViewActions.actionOnItem<TaskViewHolder>(
                    hasDescendant(
                        withText("Task1")
                    ), click()
                )
            )

            val backStack = navController.backStack
            val currentDestination = backStack.last()
            assertThat(currentDestination.destination.id).isEqualTo(R.id.taskFragment)
        }
    }
}