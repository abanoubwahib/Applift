package com.applift.project

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.applift.App
import com.applift.R
import com.applift.dashboard.DashboardFragment
import com.applift.dashboard.adapter.ProjectsViewHolder
import com.applift.di.DaggerAppComponent
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProjectFragmentTest {

    @Before
    fun init() {
        DaggerAppComponent.builder().application(App()).build()
    }

    @Test
    fun addTaskTest() {

        runBlocking {
            val navController = TestNavHostController(
                ApplicationProvider.getApplicationContext()
            ).apply {
                setGraph(R.navigation.home)
                setCurrentDestination(R.id.dashboardFragment)
            }

            val dashboardScenario = launchFragmentInContainer<DashboardFragment>()

            dashboardScenario.onFragment { fragment ->
                Navigation.setViewNavController(fragment.requireView(), navController)
            }

            Espresso.onView(withId(R.id.fab)).perform(click())

            Espresso.onView(withId(R.id.project)).perform(typeText("Project1"))

            Espresso.onView(withId(R.id.add)).perform(click())

            Espresso.onView(withId(R.id.rvProjects)).perform(
                RecyclerViewActions.actionOnItem<ProjectsViewHolder>(
                    hasDescendant(
                        withText("Project1")
                    ), click()
                )
            )

            Espresso.onView(withId(R.id.fab)).perform(click())

            Espresso.onView(withId(R.id.title)).perform(typeText("Task1"))
            
            Espresso.onView(withId(R.id.description)).perform(typeText("Description1"))

            Espresso.onView(withId(R.id.add)).perform(click())

            Espresso.onView(withId(R.id.rvTasks)).perform(
                RecyclerViewActions.actionOnItem<ProjectsViewHolder>(
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