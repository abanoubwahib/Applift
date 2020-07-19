package com.applift.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.applift.data.ViewModelFactory
import com.applift.ui.dashboard.DashboardViewModel
import com.applift.ui.project.ProjectViewModel
import com.applift.ui.task.TaskViewModel
import com.applift.ui.task.dialog.EditTaskViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(viewModel: DashboardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProjectViewModel::class)
    abstract fun bindProjectViewModel(viewModel: ProjectViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TaskViewModel::class)
    abstract fun bindTaskViewModel(viewModel: TaskViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditTaskViewModel::class)
    abstract fun bindEditTaskViewModel(viewModel: EditTaskViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}