package com.applift.ui.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.applift.R
import com.applift.data.ViewModelFactory
import com.applift.data.model.Task
import com.applift.databinding.FragmentProjectBinding
import com.applift.extensions.observe
import com.applift.extensions.showToast
import com.applift.extensions.toGone
import com.applift.extensions.toVisible
import com.applift.listeners.AddTaskCallback
import com.applift.ui.base.BaseFragment
import com.applift.ui.project.adapter.TasksAdapter
import com.applift.ui.project.dialog.AddTaskFragment
import com.applift.utils.Event
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ProjectFragment : BaseFragment(), AddTaskCallback {
    private lateinit var binding: FragmentProjectBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ProjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initViewBinding() {
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvTasks.layoutManager = layoutManager
        binding.rvTasks.setHasFixedSize(true)

        binding.fab.setOnClickListener {
            showAddTaskDialog()
        }
    }

    override fun initializeViewModel() {
        viewModel = viewModelFactory.create(ProjectViewModel::class.java)
        viewModel.getScreenTitle()?.let {
            binding.projectTitle.text = it
        }

        viewModel.getTasks()
    }

    override fun observeViewModel() {
        observe(viewModel.tasksLiveData, ::bindListData)
        observe(viewModel.insertTaskLiveData, ::showToast)
        observe(viewModel.noDataLiveData, ::showNoDataView)
        observe(viewModel.openTaskDetailsPrivate, ::navigateToTaskDetails)
    }

    private fun showAddTaskDialog() {
        val dialogFragment = AddTaskFragment(this)
        dialogFragment.isCancelable = true
        if (activity?.supportFragmentManager != null)
            dialogFragment.show(activity?.supportFragmentManager!!, "dialog_add_task")
    }

    override fun onTaskAdded(task_name: String, task_description: String) {
        viewModel.onAddTask(task_name, task_description)
    }

    private fun showNoDataView(event: @ParameterName(name = "t") Event<Any>) {
        binding.relativeNoTasks.toVisible()
        binding.rvTasks.toGone()
    }

    private fun bindListData(list: @ParameterName(name = "t") List<Task>) {
        binding.relativeNoTasks.toGone()
        binding.rvTasks.toVisible()
        val adapter = TasksAdapter(projectViewModel = viewModel, tasks = list)
        binding.rvTasks.adapter = adapter
    }

    private fun navigateToTaskDetails(event: @ParameterName(name = "t") Event<Any>) {
        if (!event.hasBeenHandled) {
            findNavController().navigate(R.id.action_projectFragment_to_taskFragment, null)
        }
    }

    private fun showToast(l: @ParameterName(name = "t") Long) {
        if (l != -1L)
            showToast(getString(R.string.project_added_successfully))
        else
            showToast(getString(R.string.project_added_failure))
    }
}