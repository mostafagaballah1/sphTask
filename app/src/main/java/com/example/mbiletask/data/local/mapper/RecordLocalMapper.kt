package com.example.mbiletask.data.local.mapper

import com.example.mbiletask.data.local.model.RecordLocal
import com.example.mbiletask.data.remote.model.Record
import com.example.mbiletask.utils.Mapper
import javax.inject.Inject

class RecordLocalMapper @Inject constructor() : Mapper<Record, RecordLocal> {

    override fun from(e: RecordLocal): Record {
        return Record(
            _id = e._id,
            quarter = e.quarter,
            volume_of_mobile_data = e.volume_of_mobile_data,
            decrease = e.decrease
        )
    }

    override fun to(t: Record): RecordLocal {
        return RecordLocal(
            _id = t._id,
            quarter = t.quarter,
            volume_of_mobile_data = t.volume_of_mobile_data,
            decrease = t.decrease
        )
    }
}