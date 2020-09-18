package com.example.mbiletask.utils.di

import android.app.Application
import androidx.room.Room
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

@Module(includes = [LocalPersistenceModule.Binders::class])
class LocalPersistenceModule {

    @Module
    interface Binders {

        @Binds
        fun bindsLocalDataSource(
            localDataSourceImpl: LocalDataSourceImp
        ): LocalDataSource

        @Binds
        fun bindRecordsLocalMapper(
            CRecordLocalMapper: RecordLocalMapper
        ): Mapper<Record, RecordLocal>

    }

    @Provides
    @Singleton
    fun providesDatabase(
        application: Application
    ) = Room.inMemoryDatabaseBuilder(application, RecordsDB::class.java)
        .allowMainThreadQueries()
        .build()

    @Provides
    @Singleton
    fun providesRecordsDAO(
        recordsDB: RecordsDB
    ) = recordsDB.getRecordsDAO()


}
