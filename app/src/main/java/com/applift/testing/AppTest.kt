package com.applift.utils.testing

import androidx.annotation.VisibleForTesting
import com.applift.App

@VisibleForTesting
class AppTest : App() {

    override fun initDagger() {
        DaggerTestAppComponent.builder().application(this)
            .build().inject(this)
    }
}