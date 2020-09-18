package com.example.mbiletask.di.module

import com.example.mbiletask.data.repository.RecordsRepository
import com.example.mbiletask.data.repository.RecordsRepositoryImp
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindsRepository(
        repoImpl: RecordsRepositoryImp
    ): RecordsRepository
}