package com.mostafa.assignment.di.builder

import androidx.lifecycle.ViewModel
import com.mostafa.assignment.di.qualifier.ViewModelKey
import com.mostafa.assignment.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AppViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel
}