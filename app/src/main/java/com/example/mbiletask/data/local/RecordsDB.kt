package com.example.mbiletask.data.local

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mbiletask.data.local.model.*

@Database(
    entities = [RecordLocal::class],
    version = 5)
abstract class RecordsDB : RoomDatabase() {

    companion object {
        private val LOCK = Any()
        private const val DATABASE_NAME = "records.db"

        @Volatile
        private var INSTANCE: RecordsDB? = null

        fun getInstance(@NonNull context: Context): RecordsDB {
            if (INSTANCE == null) {
                synchronized(LOCK) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            RecordsDB::class.java,
                            DATABASE_NAME
                        ).fallbackToDestructiveMigration().build()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    abstract fun getRecordsDAO(): RecordsDAO
}