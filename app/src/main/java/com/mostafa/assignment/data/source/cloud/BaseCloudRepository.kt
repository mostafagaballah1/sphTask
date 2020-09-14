package com.mostafa.assignment.data.source.cloud

import com.mostafa.assignment.domain.model.ResultResponse

interface BaseCloudRepository {
   suspend fun getHome(): ResultResponse
}