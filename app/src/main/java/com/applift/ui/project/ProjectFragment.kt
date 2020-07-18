package com.applift.ui.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.applift.R
import com.applift.ui.ViewModelFactory
import com.applift.di.Injectable
import javax.inject.Inject

class ProjectFragment : Fragment(R.layout.project_fragment), Injectable {

    companion object {
        fun newInstance() = ProjectFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ProjectViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = viewModelFactory.create(ProjectViewModel::class.java)
    }
}