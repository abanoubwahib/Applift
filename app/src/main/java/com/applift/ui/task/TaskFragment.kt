package com.applift.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.applift.data.ViewModelFactory
import com.applift.databinding.FragmentTaskBinding
import com.applift.listeners.TaskPopupMenuListener
import com.applift.ui.base.BaseFragment
import com.applift.utils.PopMenuUtils
import javax.inject.Inject


class TaskFragment : BaseFragment(), TaskPopupMenuListener {

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
        viewModel.getScreenTitle()?.let {
            binding.title.text = it
        }

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvComments.layoutManager = layoutManager
        binding.rvComments.setHasFixedSize(true)

        binding.ivOptions.setOnClickListener {
            showOptionsMenu()
        }
    }


    private fun showOptionsMenu() {
        PopMenuUtils.showTaskOptionsPopMenu(binding.ivOptions,this)
    }

    override fun observeViewModel() {
    }

    override fun onEditClicked() {
        TODO("Not yet implemented")
    }

    override fun onSendToReviewClicked() {
        TODO("Not yet implemented")
    }
}