package com.example.mbiletask.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mbiletask.data.Status
import com.example.mbiletask.data.repository.RecordsRepository
import com.example.mbiletask.ui.HomeViewModel
import com.example.mbiletask.utils.RxjavaTestUtils
import com.example.mbiletask.utils.TestDataGenerator
import io.reactivex.Observable
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class HomeViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: RecordsRepository
    @get:Rule
    var rxSchedulersOverrideRule = RxjavaTestUtils()
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        homeViewModel = HomeViewModel(
            TestDataGenerator.getRecordsUseCase(repository)
        )
    }

    @Test
    fun test_getRecordsData_success() {
        Mockito.`when`(repository.getRecords()).thenReturn(Observable.just(TestDataGenerator.generateRemoteRecords().result.records))
        homeViewModel.getRecordsData()
        val recordsSource = homeViewModel.recordsLiveData

        recordsSource.observeForever {}
        Assert.assertTrue(
            recordsSource.value?.status == Status.SUCCESS
                    && recordsSource.value?.data == TestDataGenerator.generateRecordsData()
        )
    }

    @Test
    fun test_getRecordsData_error() {
        val errorMsg = "fetch error"

        Mockito.`when`(repository.getRecords()).thenReturn(Observable.error(Throwable(errorMsg)))
        homeViewModel.getRecordsData()

        val recordsSource = homeViewModel.recordsLiveData
        recordsSource.observeForever {}

        Assert.assertTrue(
            recordsSource.value?.status == Status.ERROR
                    && recordsSource.value?.message == errorMsg
        )
    }

}