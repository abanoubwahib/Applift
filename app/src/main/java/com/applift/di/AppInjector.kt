package com.applift.di

import com.applift.App

object AppInjector {

    fun init(app: App) {
        DaggerAppComponent.builder().application(app)
            .build().inject(app)
    }
}