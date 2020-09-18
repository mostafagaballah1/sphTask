package com.example.mbiletask.data.repository

import com.example.mbiletask.data.remote.model.Record
import io.reactivex.Observable

interface RecordsRepository {

    fun getRecords(): Observable<List<Record>>

}