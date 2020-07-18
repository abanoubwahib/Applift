package com.applift.extensions

import android.app.Service
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.showKeyboard() {
    (context?.getSystemService(Service.INPUT_METHOD_SERVICE) as? InputMethodManager)
            ?.showSoftInput(this, 0)
}

fun View.hideKeyboard() {
    (context?.getSystemService(Service.INPUT_METHOD_SERVICE) as? InputMethodManager)
            ?.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}