package com.applift.ui.dashboard

import androidx.lifecycle.ViewModel
import com.applift.data.repository.DataRepositorySource
import javax.inject.Inject

class DashboardViewModel
@Inject constructor(private val mDataRepo: DataRepositorySource) : ViewModel() {

}