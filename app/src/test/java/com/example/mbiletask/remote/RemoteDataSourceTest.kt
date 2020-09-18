package com.example.mbiletask.remote

import com.example.mbiletask.data.remote.ApiService
import com.example.mbiletask.data.remote.RemoteDataSource
import com.example.mbiletask.data.remote.RemoteDataSourceImp
import com.example.mbiletask.utils.TestDataGenerator
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class RemoteDataSourceTest {

    @Mock
    private lateinit var apiService: ApiService
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        remoteDataSource = RemoteDataSourceImp(
            apiService
        )
    }

    @Test
    fun test_getRecords_success() {
        val recordsResponse = TestDataGenerator.generateRemoteRecords()

        Mockito.`when`(apiService.getRecords())
            .thenReturn(Observable.just(recordsResponse))

        remoteDataSource.getRecords()
            .test()
            .assertSubscribed()
            .assertValue {
                it == recordsResponse.result.records && it[0] == recordsResponse.result.records[0]
            }.assertComplete()

        Mockito.verify(apiService, times(1))
            .getRecords()
    }

    @Test
    fun test_getRecords_error() {
        val errorMsg = "ERROR"

        Mockito.`when`(apiService.getRecords())
            .thenReturn(Observable.error(Throwable(errorMsg)))

        remoteDataSource.getRecords()
            .test()
            .assertSubscribed()
            .assertError {
                it.message == errorMsg
            }.assertNotComplete()
    }

}