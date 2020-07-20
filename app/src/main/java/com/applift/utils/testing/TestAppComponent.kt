/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.applift.utils.testing

import com.applift.TestDataModule
import com.applift.di.AppModule
import com.applift.di.FragmentBuildersModule
import com.applift.di.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        TestDataModule::class,
        ViewModelModule::class,
        FragmentBuildersModule::class
    ]
)
interface TestAppComponent : AndroidInjector<AppTest> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: AppTest): Builder

        fun build(): TestAppComponent
    }
}