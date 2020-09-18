package com.example.mbiletask.data.remote

import com.example.mbiletask.data.remote.model.Record
import io.reactivex.Observable

interface RemoteDataSource {

    fun getRecords(): Observable<List<Record>>

}