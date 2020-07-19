package com.applift.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.applift.data.ViewModelFactory
import com.applift.databinding.FragmentTaskBinding
import com.applift.ui.base.BaseFragment
import javax.inject.Inject

class TaskFragment : BaseFragment() {

    private lateinit var binding: FragmentTaskBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initializeViewModel() {
        viewModel = viewModelFactory.create(TaskViewModel::class.java)
    }

    override fun initViewBinding() {
    }

    override fun observeViewModel() {
    }
}