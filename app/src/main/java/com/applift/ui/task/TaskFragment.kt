package com.applift.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.applift.data.ViewModelFactory
import com.applift.databinding.TaskFragmentBinding
import com.applift.ui.base.BaseFragment
import javax.inject.Inject

class TaskFragment : BaseFragment() {

    private lateinit var binding: TaskFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TaskFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initViewBinding() {
    }

    override fun initializeViewModel() {
        viewModel = viewModelFactory.create(TaskViewModel::class.java)
    }

    override fun observeViewModel() {
    }
}