package com.example.mbiletask

import com.example.mbiletask.di.component.DaggerMainComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.internal.functions.Functions
import io.reactivex.plugins.RxJavaPlugins

class MainApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler(Functions.emptyConsumer())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerMainComponent
            .builder()
            .application(this)
            .build()
    }
}