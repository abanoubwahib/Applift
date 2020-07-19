package com.applift.ui.task

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applift.data.model.Comment
import com.applift.data.model.Task
import com.applift.data.repository.DataRepositorySource
import com.applift.utils.Event
import com.applift.utils.IN_REVIEW
import com.applift.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class TaskViewModel @Inject
constructor(private val mDataRepo: DataRepositorySource) : ViewModel() {

    fun getTaskDetails() {
        viewModelScope.launch {
            wrapEspressoIdlingResource {
                mDataRepo.getTaskById()?.collect {
                    _taskDetailsLiveData.value = it
                }
            }
        }
    }

    fun getScreenTitle(): String? {
        return mDataRepo.getSavedProjectTitle()
    }

    fun addComment(comment: String) {
        viewModelScope.launch {
            wrapEspressoIdlingResource {
                mDataRepo.insertComment(comment)?.collect {
                    Log.d("Data Inserted", it.toString())
                    getComments()
                }
            }
        }
    }

    fun getComments() {
        viewModelScope.launch {
            wrapEspressoIdlingResource {
                mDataRepo.getAllComments()?.collect {
                    if (it.isEmpty()) {
                        _noDataLiveData.value = Event(Any())
                    } else {
                        _commentsLiveData.value = it
                    }
                }
            }
        }
    }

    fun onSendToReviewClicked() {
        viewModelScope.launch {
            wrapEspressoIdlingResource {
                mDataRepo.updateTaskStatus(IN_REVIEW)?.collect {
                    if (it != -1) {
                        getTaskDetails()
                    }
                }
            }
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            wrapEspressoIdlingResource {
                mDataRepo.updateTask(task)?.collect {
                    if (it != -1L) {
                        getTaskDetails()
                    }
                }
            }
        }
    }

    @VisibleForTesting
    val _taskDetailsLiveData = MutableLiveData<Task>()
    val taskDetailsLiveData: LiveData<Task> get() = _taskDetailsLiveData

    @VisibleForTesting
    val _commentsLiveData = MutableLiveData<List<Comment>>()
    val commentsLiveData: LiveData<List<Comment>> get() = _commentsLiveData

    @VisibleForTesting
    val _noDataLiveData = MutableLiveData<Event<Any>>()
    val noDataLiveData: LiveData<Event<Any>> get() = _noDataLiveData
}