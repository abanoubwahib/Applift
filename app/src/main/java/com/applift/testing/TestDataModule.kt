package com.applift

import androidx.annotation.VisibleForTesting
import com.applift.repository.DataRepositorySource
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