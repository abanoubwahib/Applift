package com.applift.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.applift.R
import com.applift.ui.ViewModelFactory
import com.applift.di.Injectable
import javax.inject.Inject

class DashboardFragment : Fragment(R.layout.dashboard_fragment), Injectable {

    companion object {
        fun newInstance() = DashboardFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    private lateinit var viewModel: DashboardViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = viewModelFactory.create(DashboardViewModel::class.java)
    }
}