package com.applift.ui.task

import androidx.lifecycle.ViewModel
import com.applift.data.repository.DataRepositorySource
import javax.inject.Inject

class TaskViewModel @Inject
constructor(private val mDataRepo: DataRepositorySource) : ViewModel() {
    fun getScreenTitle(): String? {
        return mDataRepo.getTaskTitle()
    }
}