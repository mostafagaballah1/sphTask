package com.mostafa.assignment.domain.usecase

import com.mostafa.assignment.data.mapper.CloudErrorMapper
import com.mostafa.assignment.domain.model.RecordDto
import com.mostafa.assignment.domain.repository.AppRepository
import com.mostafa.assignment.domain.usecase.base.UseCase
import javax.inject.Inject

class InsertRecordsUseCase @Inject constructor(
    errorUtil: CloudErrorMapper,
    private val appRepository: AppRepository
) : UseCase<Long>(errorUtil) {
    var recordDto = RecordDto(arrayListOf())
    override suspend fun executeOnBackground(): Long {
        return appRepository.saveRecords(recordDto)
    }

}