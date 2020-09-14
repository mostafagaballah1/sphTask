package com.mostafa.assignment.di.module

import android.app.Application
import androidx.room.Room
import com.mostafa.assignment.data.source.db.AppDatabase
import com.mostafa.assignment.data.source.db.RecordDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): AppDatabase {
        return Room
                .databaseBuilder(application, AppDatabase::class.java, AppDatabase.DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    fun provideUserDao(appDataBase: AppDatabase): RecordDao {
        return appDataBase.recordDao()
    }
}