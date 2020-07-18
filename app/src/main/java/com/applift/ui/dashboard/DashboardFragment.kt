package com.applift.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.applift.R
import com.applift.data.ViewModelFactory
import com.applift.databinding.DashboardFragmentBinding
import com.applift.ui.base.BaseFragment
import javax.inject.Inject

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
        binding.fab.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_dashboardFragment_to_projectFragment)
        }
    }

    override fun initializeViewModel() {
        viewModel = viewModelFactory.create(DashboardViewModel::class.java)
    }

    override fun observeViewModel() {
    }
}