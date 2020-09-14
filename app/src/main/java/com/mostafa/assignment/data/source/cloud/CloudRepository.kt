package com.mostafa.assignment.data.source.cloud

import com.mostafa.assignment.data.restful.ApiService
import com.mostafa.assignment.domain.model.ResultResponse

class CloudRepository(private val apIs: ApiService) : BaseCloudRepository {
    override suspend fun getHome(): ResultResponse {
        return apIs.getHome().await()
    }
}
