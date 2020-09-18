package com.example.mbiletask.data.remote

import com.example.mbiletask.data.remote.model.Record
import io.reactivex.Observable
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {

    override fun getRecords(): Observable<List<Record>> {
        return apiService.getRecords()
            .map { response ->
                response.result.records
            }
    }

}