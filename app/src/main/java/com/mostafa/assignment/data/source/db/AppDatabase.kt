package com.mostafa.assignment.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mostafa.assignment.domain.model.Record

@Database(entities = [Record::class], version = AppDatabase.VERSION)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "records.db"
        const val VERSION = 3
    }
    abstract fun recordDao(): RecordDao
}