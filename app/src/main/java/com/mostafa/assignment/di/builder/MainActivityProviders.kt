package com.mostafa.assignment.di.builder

import com.mostafa.assignment.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityProviders{
    @ContributesAndroidInjector
    abstract fun provideHomeFragment(): HomeFragment

}