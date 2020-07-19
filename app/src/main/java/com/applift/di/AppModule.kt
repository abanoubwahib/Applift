package com.applift.di

import android.content.Context
import androidx.room.Room
import com.applift.App
import com.applift.data.persistence.CommentDao
import com.applift.data.persistence.MainDatabase
import com.applift.data.persistence.ProjectDao
import com.applift.data.persistence.TaskDao
import com.applift.data.local.LocalData
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideContext(app: App): Context {
        return app.applicationContext
    }

    @Singleton
    @Provides
    fun provideMainDatabase(app: App): MainDatabase {
        return Room.databaseBuilder(app, MainDatabase::class.java, "applift-db")
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideCurrencyDao(database: MainDatabase): ProjectDao {
        return database.getProjectDao()
    }

    @Singleton
    @Provides
    fun provideTaskDao(database: MainDatabase): TaskDao {
        return database.getTaskDao()
    }

    @Singleton
    @Provides
    fun provideCommentDao(database: MainDatabase): CommentDao {
        return database.getCommentDao()
    }

    @Singleton
    @Provides
    fun provideLocaleData(
        projectDao: ProjectDao,
        taskDao: TaskDao,
        commentDao: CommentDao
    ): LocalData {
        return LocalData(projectDao, taskDao, commentDao)
    }
}