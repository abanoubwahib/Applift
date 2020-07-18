package com.applift.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.applift.R
import com.applift.data.Resource
import com.applift.data.ViewModelFactory
import com.applift.data.model.Project
import com.applift.databinding.DashboardFragmentBinding
import com.applift.extensions.observe
import com.applift.extensions.toGone
import com.applift.extensions.toVisible
import com.applift.ui.base.BaseFragment
import com.applift.ui.dashboard.adapter.ProjectsAdapter
import javax.inject.Inject

@Suppress("DEPRECATION")
class DashboardFragment : BaseFragment() {

    private lateinit var binding: DashboardFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DashboardFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initViewBinding() {

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvProjects.layoutManager = layoutManager
        binding.rvProjects.setHasFixedSize(true)

        binding.fab.setOnClickListener {
            viewModel.showAddDialog(fragmentManager)
        }
    }

    override fun initializeViewModel() {
        viewModel = viewModelFactory.create(DashboardViewModel::class.java)
    }

    override fun observeViewModel() {
        observe(viewModel.projectsLiveData, ::handleProjectList)
        observe(viewModel.navigateToTasksLiveData, ::handleNavigateToTasks)
    }

    private fun handleNavigateToTasks(navigateToTasks: Boolean) {
        if(navigateToTasks)
        Navigation.findNavController(binding.fab)
            .navigate(R.id.action_dashboardFragment_to_projectFragment)
    }

    private fun handleProjectList(projectModel: Resource<List<Project>>) {
        when (projectModel) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> projectModel.data?.let {
                hideLoadingView()
                bindListData(projects = it)
            }
            is Resource.DataError -> {
                hideLoadingView()
            }
        }
    }

    private fun hideLoadingView() {
        binding.progressBar.toGone()
    }

    private fun showLoadingView() {
        binding.progressBar.toVisible()
    }

    private fun bindListData(projects: List<Project>) {
        val adapter = ProjectsAdapter(dashboardViewModel = viewModel, projects = projects)
        binding.rvProjects.adapter = adapter
    }
}