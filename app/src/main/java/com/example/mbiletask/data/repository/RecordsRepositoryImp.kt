package com.example.mbiletask.data.repository

import com.example.mbiletask.data.local.LocalDataSource
import com.example.mbiletask.data.remote.RemoteDataSource
import com.example.mbiletask.data.remote.model.Record
import io.reactivex.Observable
import javax.inject.Inject

class RecordsRepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : RecordsRepository {

    override fun getRecords(): Observable<List<Record>> {
        val localSource = localDataSource.getRecords()
        return remoteDataSource.getRecords().map {
            localDataSource.saveRecords(it)
            it
        }.onErrorResumeNext(Observable.empty()).concatWith(localSource)
    }

}