package com.mostafa.assignment.di.component

import android.app.Application
import com.mostafa.assignment.core.App
import com.mostafa.assignment.di.builder.ActivityBuilder
import com.mostafa.assignment.di.module.ContextModule
import com.mostafa.assignment.di.module.DataBaseModule
import com.mostafa.assignment.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton
import dagger.android.support.AndroidSupportInjectionModule

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, NetworkModule::class, ActivityBuilder::class, ContextModule::class, DataBaseModule::class])
interface CoreComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): CoreComponent
    }


}