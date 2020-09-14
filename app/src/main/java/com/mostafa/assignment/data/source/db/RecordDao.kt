package com.mostafa.assignment.data.source.db

import androidx.room.*
import com.mostafa.assignment.domain.model.Record


@Dao
interface RecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: Record): Long

    @Delete
   suspend fun deleteRecord(record: Record): Int

    @Query("SELECT * from Record")
   suspend fun selectAllRecords(): MutableList<Record>

    @Query("DELETE FROM Record")
    fun deleteAllRecords()

}