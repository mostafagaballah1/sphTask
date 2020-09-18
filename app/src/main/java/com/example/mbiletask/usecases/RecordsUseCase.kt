package com.example.mbiletask.usecases

import com.example.mbiletask.data.remote.model.Record
import com.example.mbiletask.data.repository.RecordsRepository
import com.example.mbiletask.di.qualifier.Background
import com.example.mbiletask.di.qualifier.Foreground
import com.example.mbiletask.utils.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject


class RecordsUseCase @Inject constructor(
    private val recordsRepository: RecordsRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : ObservableUseCase<List<Record>>(
    backgroundScheduler,
    foregroundScheduler
) {
    override fun generateObservable(): Observable<List<Record>> {
        return recordsRepository.getRecords()
    }
}