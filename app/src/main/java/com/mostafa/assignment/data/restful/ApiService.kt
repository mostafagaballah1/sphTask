package com.mostafa.assignment.data.restful

import com.mostafa.assignment.domain.model.ResultResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {

    @GET("api/action/datastore_search?resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f&limit=59")
    fun getHome(
    ): Deferred<ResultResponse>


}