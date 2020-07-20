package com.applift.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.applift.R
import com.applift.data.ViewModelFactory
import com.applift.data.model.Project
import com.applift.databinding.FragmentDashboardBinding
import com.applift.utils.Event
import com.applift.extensions.observe
import com.applift.extensions.showToast
import com.applift.listeners.AddProjectCallback
import com.applift.ui.base.BaseFragment
import com.applift.ui.dashboard.adapter.ProjectsAdapter
import com.applift.ui.dashboard.dialog.AddProjectFragment
import com.applift.extensions.toGone
import com.applift.extensions.toVisible
import javax.inject.Inject

@Suppress("DEPRECATION")
class DashboardFragment : BaseFragment(), AddProjectCallback {

    private lateinit var binding: FragmentDashboardBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: DashboardViewModel

    override fun onProjectAdded(project_name: String) {
        viewModel.onProjectAdd(project_name)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initViewBinding() {

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvProjects.layoutManager = layoutManager
        binding.rvProjects.setHasFixedSize(true)

        binding.fab.setOnClickListener {
            showAddProjectDialog()
        }
    }

    override fun initializeViewModel() {
        viewModel = viewModelFactory.create(DashboardViewModel::class.java)
        viewModel.getProjects()
    }

    override fun observeViewModel() {
        observe(viewModel.projectsLiveData, ::bindListData)
        observe(viewModel.insertProjectLiveData, ::showToast)
        observe(viewModel.noDataLiveData, ::showNoDataView)
        observe(viewModel.openProjectTasksPrivate, ::navigateToTasks)
    }

    private fun showToast(l: @ParameterName(name = "t") Long) {
        if (l != -1L)
            showToast(getString(R.string.project_added_successfully))
        else
            showToast(getString(R.string.project_added_failure))
    }

    private fun showNoDataView(event: @ParameterName(name = "t") Event<Any>) {
        if (!event.hasBeenHandled) {
            binding.relativeNoData.toVisible()
            binding.rvProjects.toGone()
        }
    }

    private fun bindListData(projects: List<Project>) {
        binding.rvProjects.toVisible()
        binding.relativeNoData.toGone()
        val adapter = ProjectsAdapter(dashboardViewModel = viewModel, projects = projects)
        binding.rvProjects.adapter = adapter
    }

    private fun showAddProjectDialog() {
        val dialogFragment = AddProjectFragment(this)
        dialogFragment.isCancelable = true
        if (fragmentManager != null)
            dialogFragment.show(fragmentManager!!, "dialog_add_project")
    }

    private fun navigateToTasks(event: @ParameterName(name = "t") Event<Any>) {
        if (!event.hasBeenHandled) {
            findNavController().navigate(R.id.action_dashboardFragment_to_projectFragment, null)
        }
    }
}