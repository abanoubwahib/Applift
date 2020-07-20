package com.applift.task

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.applift.FakeDataRepository
import com.applift.R
import com.applift.data.model.Project
import com.applift.data.model.Task
import com.applift.data.repository.DataRepositorySource
import com.applift.ui.HomeActivity
import com.applift.ui.task.TaskFragment
import com.applift.utils.testing.AppTest
import com.applift.utils.testing.DaggerTestAppComponent
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TaskFragmentTest {

    lateinit var mDataRepository: DataRepositorySource

    @Before
    fun init() {
        DaggerTestAppComponent.builder().application(AppTest()).build()
        mDataRepository = FakeDataRepository()
    }

    @Test
    fun addCommentTest() {
        val project = Project("project_name")
        project.id = 1

        mDataRepository.saveProject(project)

        val task = Task("Task1", "Description1", "20-7-2020", project.id.toString())

        mDataRepository.saveTask(task)

        launchFragmentInContainer<TaskFragment>(null, R.style.AppTheme)
        onView(withId(R.id.editComment)).check(matches(isDisplayed()))
        onView(withId(R.id.send)).check(matches(isDisplayed()))

        onView(withId(R.id.editComment)).perform(typeText("Comment1"))
        onView(withId(R.id.send)).perform(click())

        onView(withId(R.id.relativeNoComments)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.rvComments)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }
}