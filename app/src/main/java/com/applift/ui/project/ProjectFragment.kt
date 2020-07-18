package com.applift.ui.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.applift.R
import com.applift.data.ViewModelFactory
import com.applift.databinding.ProjectFragmentBinding
import com.applift.ui.base.BaseFragment
import javax.inject.Inject

class ProjectFragment : BaseFragment() {
    private lateinit var binding: ProjectFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ProjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ProjectFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initViewBinding() {
        binding.fab.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_projectFragment_to_taskFragment)
        }
    }

    override fun initializeViewModel() {
        viewModel = viewModelFactory.create(ProjectViewModel::class.java)
    }

    override fun observeViewModel() {
    }
}