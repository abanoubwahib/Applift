package com.applift.ui.project

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.applift.R
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat

@RunWith(AndroidJUnit4::class)
class ProjectFragmentTest {

    @Test
    fun testNavigateToTask() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        ).apply {
            setGraph(R.navigation.home)
            setCurrentDestination(R.id.projectFragment)
        }

        val dashboardScenario = launchFragmentInContainer<ProjectFragment>()

        dashboardScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(click())

        val backStack = navController.backStack
        val currentDestination = backStack.last()

        assertThat(currentDestination.destination.id).isEqualTo(R.id.taskFragment)
    }
}