package com.mostafa.assignment.data.repository

import com.mostafa.assignment.data.source.cloud.BaseCloudRepository
import com.mostafa.assignment.data.source.db.RecordDao
import com.mostafa.assignment.domain.model.Record
import com.mostafa.assignment.domain.model.RecordDto
import com.mostafa.assignment.domain.model.ResultResponse
import com.mostafa.assignment.domain.repository.AppRepository
import javax.inject.Inject

class AppRepoImp @Inject constructor(
    private val cloudRepository: BaseCloudRepository,
    private val recordDao: RecordDao
) : AppRepository {
    override suspend fun selectAllRecords(): MutableList<Record> {
        return recordDao.selectAllRecords()
    }

    override suspend fun saveRecords(recordDto: RecordDto): Long {
        if (recordDto.results.size > 0) {
            recordDao.deleteAllRecords()
            for (record in recordDto.results) {
                recordDao.insertRecord(record)
            }
        }
        return 0L
    }

    override suspend fun getHome(): ResultResponse {
        return cloudRepository
            .getHome()
    }


}