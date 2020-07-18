package com.applift.ui.project

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.applift.R
import com.applift.data.ViewModelFactory
import com.applift.di.Injectable
import kotlinx.android.synthetic.main.project_fragment.*
import javax.inject.Inject

class ProjectFragment : Fragment(R.layout.project_fragment), Injectable {

    companion object {
        fun newInstance() = ProjectFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ProjectViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModelFactory.create(ProjectViewModel::class.java)

        fab.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_projectFragment_to_taskFragment)
        }
    }
}