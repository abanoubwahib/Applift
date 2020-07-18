package com.applift.di

import com.applift.data.repository.DataRepository
import com.applift.data.repository.DataRepositorySource
import com.applift.data.local.LocalData
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class RepositoryModule {

    @Singleton
    @Provides
    fun provideDataRepository(localData: LocalData): DataRepositorySource {
        return DataRepository(localData = localData)
    }
}