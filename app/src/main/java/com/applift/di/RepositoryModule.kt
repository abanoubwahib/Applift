package com.applift.di

import com.applift.data.local.LocalData
import com.applift.repository.DataRepository
import com.applift.repository.DataRepositorySource
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