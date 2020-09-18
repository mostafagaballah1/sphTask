package com.example.mbiletask.data.local

import com.example.mbiletask.data.remote.model.Record
import io.reactivex.Observable

interface LocalDataSource {

    fun saveRecords(records: List<Record>)

    fun getRecords(): Observable<List<Record>>

}