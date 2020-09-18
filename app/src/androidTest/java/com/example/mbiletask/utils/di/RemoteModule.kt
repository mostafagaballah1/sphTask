package com.example.mbiletask.utils.di

import com.example.mbiletask.data.remote.RemoteDataSource
import com.example.mbiletask.data.remote.RemoteDataSourceImp
import com.example.mbiletask.utils.Constants
import com.example.mbiletask.utils.utils.FakeApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [RemoteModule.Binders::class])
class RemoteModule {

    @Module
    interface Binders {

        @Binds
        fun bindsRemoteSource(
            remoteDataSourceImpl: RemoteDataSourceImp
        ): RemoteDataSource

    }

    @Provides
    fun providesAPIService(retrofit: Retrofit): FakeApiService =
        FakeApiService()


    @Provides
    fun providesRetrofit(): Retrofit =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()


}