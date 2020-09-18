package com.example.mbiletask.di.component

import android.app.Application
import com.example.mbiletask.MainApplication
import com.example.mbiletask.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, ActivityModule::class,
        NetworkModule::class, ViewModelModule::class, LocalModule::class, DataModule::class]
)
interface MainComponent : AndroidInjector<MainApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): MainComponent
    }


}