package com.applift.ui.task.dialog

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applift.data.model.Task
import com.applift.repository.DataRepositorySource
import com.applift.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditTaskViewModel @Inject
constructor(private val mDataRepo: DataRepositorySource) : ViewModel() {

    @VisibleForTesting
    val _taskDetailsLiveData = MutableLiveData<Task>()
    val taskDetailsLiveData: LiveData<Task> get() = _taskDetailsLiveData

    fun getTaskDetails() {
        viewModelScope.launch {
            wrapEspressoIdlingResource {
                mDataRepo.getTaskById()?.collect {
                    _taskDetailsLiveData.value = it
                }
            }
        }
    }
}