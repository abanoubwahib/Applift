package com.applift.ui.project

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applift.data.model.Task
import com.applift.data.repository.DataRepositorySource
import com.applift.utils.Event
import com.applift.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProjectViewModel
@Inject constructor(private val mDataRepo: DataRepositorySource) : ViewModel() {

    init {
        getTasks()
    }

    private fun getTasks() {
        viewModelScope.launch {
            wrapEspressoIdlingResource {
                mDataRepo.getAllTasks()?.collect { tasks ->
                    if (tasks.isEmpty()) {
                        _noDataLiveData.value = Event(Any())
                    } else {
                        _tasksLiveData.value = tasks
                    }
                }
            }
        }
    }

    fun handleTaskClicked(task: Task) {
        mDataRepo.saveTask(task)
        _openTaskDetailsPrivate.value = Event(Any())
    }

    fun onAddTask(taskName: String, taskDescription: String) {
        viewModelScope.launch {
            wrapEspressoIdlingResource {
                mDataRepo.insertTask(taskName,taskDescription).collect {
                    Log.d("Data Inserted", it.toString())
                    getTasks()
                }
            }
        }
    }

    @VisibleForTesting
    val _tasksLiveData = MutableLiveData<List<Task>>()
    val tasksLiveData: LiveData<List<Task>> get() = _tasksLiveData

    @VisibleForTesting
    val _insertTaskLiveData = MutableLiveData<Long>()
    val insertTaskLiveData: LiveData<Long> get() = _insertTaskLiveData

    @VisibleForTesting
    val _noDataLiveData = MutableLiveData<Event<Any>>()
    val noDataLiveData: LiveData<Event<Any>> get() = _noDataLiveData

    @VisibleForTesting
    val _openTaskDetailsPrivate = MutableLiveData<Event<Any>>()
    val openTaskDetailsPrivate: LiveData<Event<Any>> get() = _openTaskDetailsPrivate
}