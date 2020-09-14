package com.mostafa.assignment.di.builder

import androidx.lifecycle.ViewModelProvider
import com.mostafa.assignment.ui.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(includes = [
    (RepositoryBuilder::class),
    (AppViewModelBuilder::class)
])
abstract class ViewModelBuilder {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}