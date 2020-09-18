package com.example.mbiletask.repository

import com.example.mbiletask.data.local.LocalDataSource
import com.example.mbiletask.data.remote.RemoteDataSource
import com.example.mbiletask.data.repository.RecordsRepository
import com.example.mbiletask.data.repository.RecordsRepositoryImp
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
class RepositoryTest {

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var localDataSource: LocalDataSource
    private lateinit var repository: RecordsRepository


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = RecordsRepositoryImp(
            remoteDataSource,
            localDataSource
        )
    }

    @Test
    fun test_getRecords_local_remote_interactions() {
        val records = TestDataGenerator.generateRemoteRecords()

        Mockito.`when`(remoteDataSource.getRecords())
            .thenReturn(Observable.just(records.result.records))

        Mockito.`when`(localDataSource.getRecords())
            .thenReturn(Observable.just(records.result.records))

        val testSubscriber = repository.getRecords().test()

        testSubscriber.assertSubscribed()
            .assertValues(
                records.result.records,
                records.result.records
            )
            .assertComplete()

        Mockito.verify(localDataSource, Mockito.times(1))
            .saveRecords(records.result.records)

        Mockito.verify(remoteDataSource, Mockito.times(1))
            .getRecords()
    }

    @Test
    fun test_getRecords_remote_error() {
        val records = TestDataGenerator.generateRemoteRecords()

        Mockito.`when`(remoteDataSource.getRecords())
            .thenReturn(Observable.error(Throwable()))
        Mockito.`when`(localDataSource.getRecords())
            .thenReturn(Observable.just(records.result.records))

        val testSubscriber = repository.getRecords().test()

        testSubscriber.assertSubscribed()
            .assertValue { res ->
                res.containsAll(records.result.records)
            }
            .assertComplete()

        Mockito.verify(localDataSource, Mockito.times(1))
            .getRecords()
    }

}