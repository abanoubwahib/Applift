package com.applift

import android.content.Context
import androidx.multidex.MultiDexApplication
import dagger.android.DispatchingAndroidInjector
import com.applift.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

open class App : MultiDexApplication(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        initDagger()
    }

    open fun initDagger() {
        AppInjector.init(this)
    }

    companion object {
        lateinit var context: Context
    }
}
