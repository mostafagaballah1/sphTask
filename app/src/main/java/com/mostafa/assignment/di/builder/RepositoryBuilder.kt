package com.mostafa.assignment.di.builder

import com.mostafa.assignment.data.repository.AppRepoImp
import com.mostafa.assignment.domain.repository.AppRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryBuilder {
    @Binds
    abstract fun bindsMovieRepository(repoImp: AppRepoImp): AppRepository
}