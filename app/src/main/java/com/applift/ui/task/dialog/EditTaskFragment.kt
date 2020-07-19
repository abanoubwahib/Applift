package com.applift.ui.task.dialog

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.applift.R
import com.applift.data.model.Task
import com.applift.data.repository.DataRepositorySource
import com.applift.databinding.DialogEditTaskFragmentBinding
import com.applift.listeners.EditTaskCallback
import com.applift.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditTaskFragment(private val callback: EditTaskCallback) :
    DialogFragment() {

    private lateinit var binding: DialogEditTaskFragmentBinding

    @Inject
    lateinit var mDataRepo: DataRepositorySource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.MyDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogEditTaskFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            wrapEspressoIdlingResource {
                mDataRepo.getTaskById()?.collect { task ->
                    binding.title.setText(task.title)
                    binding.description.setText(task.description)

                    binding.edit.setOnClickListener {
                        if (binding.title.toString().isEmpty() || binding.description.toString()
                                .isEmpty()) {
                            return@setOnClickListener
                        }
                        updateTask(task)
                        dismiss()
                    }
                }
            }
        }
    }

    private fun updateTask(task: Task) {
        task.title = binding.title.text.toString()
        task.description = binding.title.text.toString()
        callback.onTaskUpdated(task)
    }

    override fun onResume() {
        super.onResume()
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        dialog?.window?.also { window ->
            window.attributes?.also { attributes ->
                attributes.dimAmount = 0.9F
                window.attributes = attributes
                setLayoutParams(window)
            }
        }
    }

    private fun setLayoutParams(window: Window) {
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }
}