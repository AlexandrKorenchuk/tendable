package com.release.data.model

import com.google.gson.annotations.SerializedName
import com.release.domain.model.InspectionQuizItem

data class SubmitBody(
    @SerializedName("something")
    val something: List<InspectionQuizItem>?=null,
)
