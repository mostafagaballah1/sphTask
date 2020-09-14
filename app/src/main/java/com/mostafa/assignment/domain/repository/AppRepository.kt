package com.mostafa.assignment.domain.repository

import com.mostafa.assignment.domain.model.Record
import com.mostafa.assignment.domain.model.RecordDto
import com.mostafa.assignment.domain.model.ResultResponse

interface AppRepository{
    suspend fun getHome(): ResultResponse
    suspend fun saveRecords(recordDto: RecordDto): Long
    suspend fun selectAllRecords() : MutableList<Record>
}