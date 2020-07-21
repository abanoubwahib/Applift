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
import com.applift.R
import com.applift.ui.dashboard.DashboardFragment
import com.applift.ui.dashboard.adapter.ProjectsViewHolder
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DashboardFragmentTest {

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

            onView(withId(R.id.project)).perform(typeText("Project0"))

            onView(withId(R.id.add)).perform(click())

            onView(withId(R.id.rvProjects)).perform(
                RecyclerViewActions.actionOnItem<ProjectsViewHolder>(
                    ViewMatchers.hasDescendant(
                        ViewMatchers.withText("Project0")
                    ), click()
                )
            )

            val backStack = navController.backStack
            val currentDestination = backStack.last()
            assertThat(currentDestination.destination.id).isEqualTo(R.id.projectFragment)
        }
    }
}