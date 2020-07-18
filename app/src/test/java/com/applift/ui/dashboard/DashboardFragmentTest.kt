package com.applift.ui.dashboard

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import org.junit.Test

import org.junit.runner.RunWith


import androidx.test.espresso.Espresso.onView

import androidx.test.espresso.action.ViewActions.click

import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.applift.R

import com.google.common.truth.Truth.assertThat


@RunWith(AndroidJUnit4::class)
class DashboardFragmentTest {

    @Test
    fun testNavigateToProject() {

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

        val backStack = navController.backStack
        val currentDestination = backStack.last()

        assertThat(currentDestination.destination.id).isEqualTo(R.id.projectFragment)
    }
}