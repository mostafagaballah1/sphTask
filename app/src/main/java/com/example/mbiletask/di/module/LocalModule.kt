package com.example.mbiletask.di.module

import android.app.Application
import com.example.mbiletask.data.local.LocalDataSource
import com.example.mbiletask.data.local.LocalDataSourceImp
import com.example.mbiletask.data.local.RecordsDB
import com.example.mbiletask.data.local.mapper.RecordLocalMapper
import com.example.mbiletask.data.local.model.RecordLocal
import com.example.mbiletask.data.remote.model.Record
import com.example.mbiletask.utils.Mapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [LocalModule.Binders::class])
class LocalModule {

    @Module
    interface Binders {

        @Binds
        fun bindsLocalDataSource(
            localDataSourceImpl: LocalDataSourceImp
        ): LocalDataSource

        @Binds
        fun bindRecordLocalMapper(
            CRecordLocalMapper: RecordLocalMapper
        ): Mapper<Record, RecordLocal>

    }

    @Provides
    @Singleton
    fun providesDatabase(application: Application) =
        RecordsDB.getInstance(application.applicationContext)

    @Provides
    @Singleton
    fun providesRecordsDAO(recordsDB: RecordsDB) = recordsDB.getRecordsDAO()
}