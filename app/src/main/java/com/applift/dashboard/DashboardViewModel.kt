package com.applift.dashboard

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applift.data.model.Project
import com.applift.data.repository.DataRepositorySource
import com.applift.utils.Event
import com.applift.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardViewModel
@Inject constructor(private val mDataRepo: DataRepositorySource) : ViewModel() {

    fun getProjects() {
        viewModelScope.launch {
            wrapEspressoIdlingResource {
                mDataRepo.getAllProjects().collect { projects ->
                    if (projects.isEmpty()) {
                        _noDataLiveData.value = Event(Any())
                    } else {
                        _projectsLiveData.value = projects
                    }
                }
            }
        }
    }

    fun onProjectAdd(project_name: String) {
        viewModelScope.launch {
            wrapEspressoIdlingResource {
                mDataRepo.insertProject(project_name).collect {
                    Log.d("Data Inserted", it.toString())
                    getProjects()
                }
            }
        }
    }

    fun handleProjectClicked(project: Project) {
        mDataRepo.saveProject(project)
        _openProjectTasksPrivate.value = Event(Any())
    }

    @VisibleForTesting
    val _projectsLiveData = MutableLiveData<List<Project>>()
    val projectsLiveData: LiveData<List<Project>> get() = _projectsLiveData

    @VisibleForTesting
    val _insertProjectLiveData = MutableLiveData<Long>()
    val insertProjectLiveData: LiveData<Long> get() = _insertProjectLiveData

    @VisibleForTesting
    val _noDataLiveData = MutableLiveData<Event<Any>>()
    val noDataLiveData: LiveData<Event<Any>> get() = _noDataLiveData

    @VisibleForTesting
    val _openProjectTasksPrivate = MutableLiveData<Event<Any>>()
    val openProjectTasksPrivate: LiveData<Event<Any>> get() = _openProjectTasksPrivate
}