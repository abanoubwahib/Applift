package com.applift.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.applift.R
import com.applift.data.ViewModelFactory
import com.applift.di.Injectable
import kotlinx.android.synthetic.main.dashboard_fragment.*
import javax.inject.Inject

class DashboardFragment : Fragment(R.layout.dashboard_fragment), Injectable {

    companion object {
        fun newInstance() = DashboardFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    private lateinit var viewModel: DashboardViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModelFactory.create(DashboardViewModel::class.java)

        fab.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_dashboardFragment_to_projectFragment)
        }
    }
}