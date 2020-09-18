package com.example.mbiletask.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mbiletask.data.local.RecordsDAO
import com.example.mbiletask.data.local.RecordsDB
import com.example.mbiletask.utils.utils.TestData
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class LocalDataSourceTest {

    private lateinit var recordsDB: RecordsDB
    private lateinit var recordsDAO: RecordsDAO

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        recordsDB = Room.inMemoryDatabaseBuilder(context, RecordsDB::class.java)
            .allowMainThreadQueries()
            .build()

        recordsDAO = recordsDB.getRecordsDAO()
    }

    @After
    fun tearDown() {
        recordsDAO.clearCachedRecords().subscribe()
        recordsDB.close()
    }

    @Test
    fun test_saveAndRetrieveRecords() {
        val records = TestData.generateLocalRecords()
        val recordsCount = records.size

        recordsDAO.saveRecords(records)

        recordsDAO.getRecords()
            .test()
            .assertValue {
                records.containsAll(it)
                        && it.size == recordsCount
            }.assertNotComplete() // As Room Observables are kept alive
    }


}