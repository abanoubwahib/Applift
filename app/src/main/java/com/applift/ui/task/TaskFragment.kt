package com.applift.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.applift.R
import com.applift.data.ViewModelFactory
import com.applift.data.model.Comment
import com.applift.data.model.Task
import com.applift.databinding.FragmentTaskBinding
import com.applift.extensions.hideKeyboard
import com.applift.extensions.observe
import com.applift.extensions.toGone
import com.applift.extensions.toVisible
import com.applift.listeners.EditTaskCallback
import com.applift.listeners.TaskPopupMenuListener
import com.applift.ui.base.BaseFragment
import com.applift.ui.task.adapter.CommentsAdapter
import com.applift.ui.task.dialog.EditTaskFragment
import com.applift.utils.Event
import com.applift.utils.PopMenuUtils
import com.applift.utils.TO_DO
import javax.inject.Inject

class TaskFragment : BaseFragment(), TaskPopupMenuListener, EditTaskCallback {

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
        viewModel.getScreenTitle()?.let {
            binding.projectTitle.text = it
        }
    }

    override fun initViewBinding() {
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvComments.layoutManager = layoutManager
        binding.rvComments.setHasFixedSize(true)

        binding.ivOptions.setOnClickListener {
            showOptionsMenu()
        }

        binding.send.setOnClickListener {
            binding.comment.hideKeyboard()
            if (binding.comment.text.toString().isNotEmpty()) {
                viewModel.addComment(binding.comment.text.toString())
                binding.comment.setText("")
            }
        }
    }

    private fun showOptionsMenu() {
        PopMenuUtils.showTaskOptionsPopMenu(binding.ivOptions, this)
    }

    override fun observeViewModel() {
        observe(viewModel.taskDetailsLiveData, ::bindDetailsData)
        observe(viewModel.commentsLiveData, ::bindCommentsList)
        observe(viewModel.noDataLiveData, ::showNoDataView)

        viewModel.getTaskDetails()
        viewModel.getComments()
    }

    private fun showNoDataView(event: @ParameterName(name = "t") Event<Any>) {
        if (!event.hasBeenHandled) {
            binding.relativeNoComments.toVisible()
            binding.rvComments.toGone()
        }
    }

    private fun bindCommentsList(commentList: @ParameterName(name = "t") List<Comment>) {
        binding.rvComments.toVisible()
        binding.relativeNoComments.toGone()
        binding.rvComments.adapter = CommentsAdapter(commentList)
    }

    private fun bindDetailsData(task: @ParameterName(name = "t") Task) {
        binding.title.text = task.title
        binding.description.text = task.description
        binding.createdDate.text = task.createdDate
        binding.status.text = task.status
        if (task.status.equals(TO_DO))
            binding.status.setTextColor(ContextCompat.getColor(context!!, R.color.black))
        else
            binding.status.setTextColor(ContextCompat.getColor(context!!, R.color.colorAccent))
    }

    override fun onEditClicked() {
        val dialogFragment = EditTaskFragment(this)
        dialogFragment.isCancelable = true
        if (activity?.supportFragmentManager != null)
            dialogFragment.show(activity?.supportFragmentManager!!, "dialog_add_task")
    }

    override fun onSendToReviewClicked() {
        viewModel.onSendToReviewClicked()
    }

    override fun onTaskUpdated(task: Task) {
        viewModel.updateTask(task)
    }
}