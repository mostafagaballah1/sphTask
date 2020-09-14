package com.mostafa.assignment.domain.usecase

import com.mostafa.assignment.data.mapper.CloudErrorMapper
import com.mostafa.assignment.domain.model.ResultResponse
import com.mostafa.assignment.domain.repository.AppRepository
import com.mostafa.assignment.domain.usecase.base.UseCase
import javax.inject.Inject

class GetHomeUseCase @Inject constructor(
        errorUtil: CloudErrorMapper,
        private val appRepository: AppRepository
) : UseCase<ResultResponse>(errorUtil) {
    override suspend fun executeOnBackground(): ResultResponse {
        return appRepository.getHome()
    }


}