package com.example.mbiletask.utils

import androidx.test.espresso.IdlingResource

import androidx.test.espresso.idling.CountingIdlingResource

class EspressoIdlingResource {

    companion object {
        private val mCountingIdlingResource = CountingIdlingResource("GLOBAL")

        fun increment() {
            mCountingIdlingResource.increment()
        }

        fun decrement() {
            mCountingIdlingResource.decrement()
        }

        fun getIdlingResource(): IdlingResource? {
            return mCountingIdlingResource
        }
    }
}