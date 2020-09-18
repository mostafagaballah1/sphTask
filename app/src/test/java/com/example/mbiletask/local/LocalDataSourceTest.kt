package com.example.mbiletask.local

import com.example.mbiletask.data.local.LocalDataSource
import com.example.mbiletask.data.local.LocalDataSourceImp
import com.example.mbiletask.data.local.RecordsDAO
import com.example.mbiletask.data.local.mapper.RecordLocalMapper
import com.example.mbiletask.utils.TestDataGenerator
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class LocalDataSourceTest {

    private val recordLocalMapper = RecordLocalMapper()

    @Mock
    private lateinit var recordsDAO: RecordsDAO
    private lateinit var localDataSource: LocalDataSource

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        localDataSource = LocalDataSourceImp(recordsDAO, recordLocalMapper)
    }

    @Test
    fun test_getRecords_success() {
        val localRecords = TestDataGenerator.generateLocalRecords()

        Mockito.`when`(recordsDAO.getRecords())
            .thenReturn(Observable.just(localRecords))

        localDataSource.getRecords()
            .test()
            .assertSubscribed()
            .assertValue { records ->
                localRecords.containsAll(
                    records.map {
                        recordLocalMapper.to(it)
                    }
                )
            }.assertComplete()
    }

    @Test
    fun test_getRecords_error() {
        val errorMsg = "ERROR"

        Mockito.`when`(recordsDAO.getRecords())
            .thenReturn(Observable.error(Throwable(errorMsg)))

        localDataSource.getRecords()
            .test()
            .assertSubscribed()
            .assertError {
                it.message == errorMsg
            }.assertNotComplete()
    }

}