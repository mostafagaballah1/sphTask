package com.example.mbiletask.data.local

import androidx.room.*
import com.example.mbiletask.data.local.model.*
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface RecordsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRecords(records: List<RecordLocal>)

    @Query("select * from recordlocal")
    fun getRecords(): Observable<List<RecordLocal>>

    @Query("DELETE FROM recordlocal")
    fun clearCachedRecords(): Completable

}