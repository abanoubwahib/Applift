package com.applift.utils

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.View
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.appcompat.widget.PopupMenu
import com.applift.R
import com.applift.listeners.TaskPopupMenuListener
import kotlinx.android.synthetic.main.fragment_task.view.*

object PopMenuUtils {

    @SuppressLint("RestrictedApi")
    fun showTaskOptionsPopMenu(v: View, listener: TaskPopupMenuListener) {
        val popup =
            PopupMenu(v.context, v)
        popup.menuInflater.inflate(R.menu.task, popup.menu)

        popup.setOnMenuItemClickListener { menu_item ->
            when (menu_item.itemId) {
                R.id.action_edit -> {
                    listener.onEditClicked()
                }

                R.id.action_send_to_Review -> {
                    listener.onSendToReviewClicked()
                }
            }
            true
        }

        val menuHelper =
            MenuPopupHelper(v.context, (popup.menu as MenuBuilder), v.ivOptions)
        menuHelper.setForceShowIcon(true)
        menuHelper.gravity = Gravity.END
        menuHelper.show()
    }
}