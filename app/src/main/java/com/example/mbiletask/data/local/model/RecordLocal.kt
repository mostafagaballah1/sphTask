package com.example.mbiletask.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecordLocal(
    @PrimaryKey val _id: String,
    var quarter: String,
    var volume_of_mobile_data: String,
    var decrease : Int
)