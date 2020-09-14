package com.mostafa.assignment.domain.model

import com.google.gson.annotations.SerializedName

data class RecordDto(
    @SerializedName("records") var results: ArrayList<Record>
)