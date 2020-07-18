package com.applift.di

import com.applift.ui.dashboard.DashboardFragment
import com.applift.ui.project.ProjectFragment
import com.applift.ui.task.TaskFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeDashboardFragment(): DashboardFragment

    @ContributesAndroidInjector
    abstract fun contributeProjectFragment(): ProjectFragment

    @ContributesAndroidInjector
    abstract fun contributeTaskFragment(): TaskFragment
}