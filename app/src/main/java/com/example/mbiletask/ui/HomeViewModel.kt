package com.example.mbiletask.ui

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.example.mbiletask.data.Resource
import com.example.mbiletask.usecases.RecordsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val recordsUseCase: RecordsUseCase
) : BaseViewModel() {

    var recordsLiveData: MutableLiveData<Resource<RecordsPresentation>> = MutableLiveData()


    @SuppressLint("CheckResult")
    fun getRecordsData() {
        //EspressoIdlingResource.increment() //TODO uncomment this line when run MainActivity UI test case

        val records = recordsUseCase.buildUseCase()
        records.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                recordsLiveData.value = Resource.loading()
            }
            .subscribe(
                {
                    // EspressoIdlingResource.decrement()   //TODO uncomment this line when run MainActivity UI test case
                    var recordsLiveDataNew = RecordsPresentation(it)
                    recordsLiveData.value = Resource.success(recordsLiveDataNew)
                }, { error ->
                    // EspressoIdlingResource.decrement()   //TODO uncomment this line when run MainActivity UI test case
                    recordsLiveData.value = Resource.error(error.message!!)
                }
            )

    }

}