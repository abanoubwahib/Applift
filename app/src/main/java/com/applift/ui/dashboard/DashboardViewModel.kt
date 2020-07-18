package com.applift.ui.dashboard

import androidx.annotation.VisibleForTesting
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.applift.data.Resource
import com.applift.data.model.Project
import com.applift.data.repository.DataRepositorySource
import com.applift.listeners.AddProjectCallback
import com.applift.ui.dialog.AddProjectFragment
import javax.inject.Inject

class DashboardViewModel
@Inject constructor(private val mDataRepo: DataRepositorySource) : ViewModel(), AddProjectCallback {

    fun handleProjectClicked(project: Project) {
        mDataRepo.saveProject(project)
        _navigateToTasksLiveData.value = null
    }

    fun showAddDialog(fragmentManager: FragmentManager?) {
        val dialogFragment = AddProjectFragment(this)
        dialogFragment.isCancelable = false
        if (fragmentManager != null)
            dialogFragment.show(fragmentManager, "dialog_add_project")
    }

    override fun onProjectAdded(project_name: String) {
    }

    @VisibleForTesting
    val _projectsLiveData = MutableLiveData<Resource<List<Project>>>()
    val projectsLiveData: LiveData<Resource<List<Project>>> get() = _projectsLiveData

    val _navigateToTasksLiveData = MutableLiveData<Boolean>()
    val navigateToTasksLiveData: LiveData<Boolean> get() = _navigateToTasksLiveData
}