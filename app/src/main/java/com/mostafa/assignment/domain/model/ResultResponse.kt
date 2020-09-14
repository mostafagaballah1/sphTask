package com.mostafa.assignment.domain.model

import com.google.gson.annotations.SerializedName

data class ResultResponse(
    @SerializedName("result") var result: RecordDto
)