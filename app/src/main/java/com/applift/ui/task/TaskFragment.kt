package com.applift.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.applift.ui.ViewModelFactory
import com.applift.di.Injectable
import javax.inject.Inject

class TaskFragment : Fragment(), Injectable {

    companion object {
        fun newInstance() = TaskFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: TaskViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = viewModelFactory.create(TaskViewModel::class.java)
    }
}