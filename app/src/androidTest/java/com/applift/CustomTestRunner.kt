package com.applift

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.applift.utils.testing.AppTest

/**
 * A custom [AndroidJUnitRunner] used to replace the application used in tests with a
 * [    CustomTestRunner].
 */
class CustomTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, AppTest::class.java.name, context)
    }
}
