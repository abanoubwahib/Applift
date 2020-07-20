package com.applift

import androidx.annotation.VisibleForTesting
import com.applift.data.repository.DataRepositorySource
import com.applift.di.ViewModelModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@VisibleForTesting
@Module
class TestDataModule {

    @Singleton
    @Provides
    fun provideDataRepository(): DataRepositorySource {
        return FakeDataRepository()
    }
}