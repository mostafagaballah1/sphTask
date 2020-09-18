package com.example.mbiletask.use_cases

import com.example.mbiletask.data.repository.RecordsRepository
import com.example.mbiletask.usecases.RecordsUseCase
import com.example.mbiletask.utils.TestDataGenerator
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class RecordsUseCaseTest {

    @Mock
    private lateinit var repository: RecordsRepository
    private lateinit var recordsUseCase: RecordsUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        recordsUseCase = RecordsUseCase(
            repository,
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )
    }

    @Test
    fun test_getRecords_success() {
        val records = TestDataGenerator.generateRemoteRecords()

        Mockito.`when`(repository.getRecords())
            .thenReturn(Observable.just(records.result.records))

        val testObserver = recordsUseCase.buildUseCase().test()

        testObserver
            .assertSubscribed()
            .assertValue { it.containsAll(records.result.records) }
    }

    @Test
    fun test_getRecords_error() {
        val errorMsg = "ERROR"

        Mockito.`when`(repository.getRecords())
            .thenReturn(Observable.error(Throwable(errorMsg)))

        val testObserver = recordsUseCase.buildUseCase().test()

        testObserver
            .assertSubscribed()
            .assertError { it.message?.equals(errorMsg, false) ?: false }
            .assertNotComplete()
    }
}