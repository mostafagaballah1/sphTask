package com.example.mbiletask.utils

import io.reactivex.Observable
import io.reactivex.Scheduler

abstract class ObservableUseCase<T> constructor(
    private val backgroundScheduler: Scheduler,
    private val foregroundScheduler: Scheduler
) {
    protected abstract fun generateObservable(): Observable<T>

    fun buildUseCase(): Observable<T> {
        return generateObservable()
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
    }

}