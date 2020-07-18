package com.applift.ui.project

import androidx.lifecycle.ViewModel
import com.applift.data.repository.DataRepositorySource
import javax.inject.Inject

class ProjectViewModel@Inject
constructor(private val dataRepositoryRepository: DataRepositorySource) : ViewModel() {
}