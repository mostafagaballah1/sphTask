package com.example.mbiletask.data.local

import com.example.mbiletask.data.local.mapper.RecordLocalMapper
import com.example.mbiletask.data.remote.model.Record
import io.reactivex.Observable
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(
    private val recordsDAO: RecordsDAO,
    private val recordLocalMapper: RecordLocalMapper
) : LocalDataSource {

    override fun saveRecords(records: List<Record>) {
        recordsDAO.saveRecords(
            records.map {
                recordLocalMapper.to(it)
            }
        )
    }

    override fun getRecords(): Observable<List<Record>> {
        return recordsDAO.getRecords()
            .map { records ->
                records.map {
                    recordLocalMapper.from(it)
                }
            }
    }


}