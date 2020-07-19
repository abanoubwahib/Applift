package com.applift.dashboard

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.applift.App
import com.applift.R
import com.applift.dashboard.adapter.ProjectsViewHolder
import com.applift.di.DaggerAppComponent
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DashboardFragmentTest {

    @Before
    fun init() {
        DaggerAppComponent.builder().application(App()).build()
    }

    @Test
    fun addProjectTest() {

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

            onView(withId(R.id.fab)).perform(click())

            onView(withId(R.id.project)).perform(typeText("Project1"))

            onView(withId(R.id.add)).perform(click())

            onView(withId(R.id.rvProjects)).perform(
                RecyclerViewActions.actionOnItem<ProjectsViewHolder>(
                    ViewMatchers.hasDescendant(
                        ViewMatchers.withText("Project1")
                    ), click()
                )
            )

            val backStack = navController.backStack
            val currentDestination = backStack.last()
            assertThat(currentDestination.destination.id).isEqualTo(R.id.projectFragment)
        }
    }
}