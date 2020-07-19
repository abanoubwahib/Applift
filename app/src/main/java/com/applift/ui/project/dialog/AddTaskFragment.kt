package com.applift.ui.project.dialog

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.applift.R
import com.applift.databinding.AddTaskFragmentBinding
import com.applift.listeners.AddTaskCallback

class AddTaskFragment(private val callback: AddTaskCallback) : DialogFragment() {

    private lateinit var binding: AddTaskFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.MyDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddTaskFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.add.setOnClickListener {
            if (binding.title.equals("") || binding.description.equals("")) {
                return@setOnClickListener
            }
            callback.onTaskAdded(binding.title.text.toString(), binding.description.text.toString())
            dismiss()
        }
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
