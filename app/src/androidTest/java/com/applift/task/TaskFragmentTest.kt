package com.applift.task

import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.applift.FakeDataRepository
import com.applift.R
import com.applift.data.model.Project
import com.applift.data.model.Task
import com.applift.repository.DataRepositorySource
import com.applift.ui.task.TaskFragment
import com.applift.utils.IN_REVIEW
import com.applift.utils.testing.AppTest
import com.applift.utils.testing.DaggerTestAppComponent
import org.hamcrest.Matchers.allOf
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

    @Test
    fun editTaskTest() {
        val project = Project("project_name")
        project.id = 1

        mDataRepository.saveProject(project)

        val task = Task("Task1", "Description1", "20-7-2020", project.id.toString())

        mDataRepository.saveTask(task)

        launchFragmentInContainer<TaskFragment>(null, R.style.AppTheme)

        onView(withId(R.id.ivOptions)).check(matches(isDisplayed()))

        onView(withId(R.id.title)).check(matches(withText("Task1")))
        onView(withId(R.id.description)).check(matches(withText("Description1")))

        onView(allOf(withId(R.id.ivOptions), withParent(withId(R.id.relative))))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withText("Edit")).perform(click())

        onView(withId(R.id.title)).perform(clearText())
        onView(withId(R.id.title)).perform(typeText("Title123"))
        onView(withId(R.id.description)).perform(clearText())
        onView(withId(R.id.description)).perform(typeText("Description123"))
        onView(withId(R.id.edit)).perform(click())

        onView(withId(R.id.title)).check(matches(withText("Title123")))
        onView(withId(R.id.description)).check(matches(withText("Description123")))
    }

    @Test
    fun onSubmitToReviewTaskTest() {
        val project = Project("project_name")
        project.id = 1

        mDataRepository.saveProject(project)

        val task = Task("Task0", "Description0", "20-7-2020", project.id.toString())

        mDataRepository.saveTask(task)

        launchFragmentInContainer<TaskFragment>(null, R.style.AppTheme)

        onView(withId(R.id.ivOptions)).check(matches(isDisplayed()))

        onView(withId(R.id.title)).check(matches(withText("Task0")))
        onView(withId(R.id.description)).check(matches(withText("Description0")))

        onView(allOf(withId(R.id.ivOptions), withParent(withId(R.id.relative))))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withText("Send To Review")).perform(click())

        onView(withId(R.id.status)).check(matches(withText(IN_REVIEW)))
    }
}