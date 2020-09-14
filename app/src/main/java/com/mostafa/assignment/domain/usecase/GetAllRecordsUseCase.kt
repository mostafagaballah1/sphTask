package com.mostafa.assignment.domain.usecase

import com.mostafa.assignment.data.mapper.CloudErrorMapper
import com.mostafa.assignment.domain.model.Record
import com.mostafa.assignment.domain.repository.AppRepository
import com.mostafa.assignment.domain.usecase.base.UseCase
import javax.inject.Inject

class GetAllRecordsUseCase @Inject constructor(
    errorUtil: CloudErrorMapper,
    private val appRepository: AppRepository
) : UseCase<MutableList<Record>>(errorUtil) {
    override suspend fun executeOnBackground(): MutableList<Record> {
        return appRepository.selectAllRecords()
    }
}