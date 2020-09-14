package com.mostafa.assignment.ui.home

import androidx.lifecycle.MutableLiveData
import android.util.Log
import androidx.lifecycle.ViewModel
import com.mostafa.assignment.domain.model.Record
import com.mostafa.assignment.domain.model.RecordDto
import com.mostafa.assignment.domain.model.response.ErrorModel
import com.mostafa.assignment.domain.model.response.ErrorStatus
import com.mostafa.assignment.domain.usecase.GetAllRecordsUseCase
import com.mostafa.assignment.domain.usecase.GetHomeUseCase
import com.mostafa.assignment.domain.usecase.InsertRecordsUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getHomeUseCase: GetHomeUseCase,
    private val insertRecordsUseCase: InsertRecordsUseCase,
    private val getAllRecordsUseCase: GetAllRecordsUseCase
) : ViewModel() {
    private val TAG = HomeViewModel::class.java.simpleName
    val homeData: MutableLiveData<RecordDto> by lazy { MutableLiveData<RecordDto>() }
    val error: MutableLiveData<ErrorModel> by lazy { MutableLiveData<ErrorModel>() }
    val recordsCount: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }

    init {
        getHomeUseCase.execute {
            onComplete {
                Log.d(TAG, it.toString())
                insert(it.result)
            }
            onError { throwable ->
                if (throwable.errorStatus == ErrorStatus.NO_CONNECTION) {
                    error.value = throwable
                    doRefresh()
                } else {
                    error.value = throwable
                    doRefresh()
                }
            }
            onCancel {
            }
        }
    }

    private fun doRefresh() {
        returnRecordsInDB()
    }


    private fun insert(recordDto: RecordDto) {
        insertRecordsUseCase.recordDto = recordDto
        insertRecordsUseCase.execute {
            onComplete {
                returnRecordsInDB()
            }
            onError { throwable ->
                error.value = throwable
            }
            onCancel {
            }
        }
    }

    private fun returnRecordsInDB() {
        getAllRecordsUseCase.execute {
            onComplete {
                val recordDtoDB = RecordDto(it as ArrayList<Record>)
                recordDtoDB.results = it as ArrayList<Record>
                homeData.value = recordDtoDB
                recordsCount.value = it.size
            }
            onError {
                error.value = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        insertRecordsUseCase.unsubscribe()
        getHomeUseCase.unsubscribe()
        getAllRecordsUseCase.unsubscribe()
    }
}