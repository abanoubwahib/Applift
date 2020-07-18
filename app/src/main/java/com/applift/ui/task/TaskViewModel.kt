package com.applift.ui.task

import androidx.lifecycle.ViewModel
import com.applift.data.repository.DataRepositorySource
import javax.inject.Inject

class TaskViewModel@Inject
constructor(private val dataRepositoryRepository: DataRepositorySource) : ViewModel() {
}