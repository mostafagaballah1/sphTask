package com.example.mbiletask.utils

import com.example.mbiletask.data.local.model.RecordLocal
import com.example.mbiletask.data.remote.model.Records
import com.example.mbiletask.data.remote.model.RecordsResponse
import com.example.mbiletask.data.remote.model.Record
import com.example.mbiletask.data.repository.RecordsRepository
import com.example.mbiletask.ui.RecordsPresentation
import com.example.mbiletask.usecases.RecordsUseCase
import io.reactivex.schedulers.Schedulers

class TestDataGenerator {

    companion object {

        fun generateRemoteRecords(): RecordsResponse {
            return RecordsResponse(
                Records(
                    listOf(
                        Record("1","2009-Q1","1.22",1),
                        Record("2","2009-Q2","1.33",0),
                        Record("3","2009-Q3","1.44",0),
                        Record("4","2009-Q4","1.55",0)
                    )
                )
            )
        }

        fun generateLocalRecords(): List<RecordLocal> {
            return listOf(
                RecordLocal("1","2009-Q1","1.22",1),
                RecordLocal("2","2009-Q2","1.33",0),
                RecordLocal("3","2009-Q3","1.44",0),
                RecordLocal("4","2009-Q4","1.55",0)
            )
        }

        fun getRecordsUseCase(repository: RecordsRepository): RecordsUseCase {
            return RecordsUseCase(
                repository,
                Schedulers.trampoline(),
                Schedulers.trampoline()
            )
        }



        fun generateRecordsData(): RecordsPresentation {
            return RecordsPresentation(
                generateRemoteRecords().result.records
            )
        }


    }
}