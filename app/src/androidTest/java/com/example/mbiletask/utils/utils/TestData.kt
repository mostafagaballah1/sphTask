package com.example.mbiletask.utils.utils

import com.example.mbiletask.data.local.model.RecordLocal

class TestData {

    companion object {

        fun generateLocalRecords(): List<RecordLocal> {
            return listOf(
                RecordLocal("1","2009-Q1","1.22",1),
                RecordLocal("2","2009-Q2","1.33",0),
                RecordLocal("3","2009-Q3","1.44",0),
                RecordLocal("4","2009-Q4","1.55",0)
            )
        }

    }
}