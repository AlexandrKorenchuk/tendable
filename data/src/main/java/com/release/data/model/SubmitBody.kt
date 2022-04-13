package com.release.data.model

import com.google.gson.annotations.SerializedName
import com.release.domain.model.InspectionItem

data class SubmitBody(
    @SerializedName("something")
    val something: List<InspectionItem>?=null,
)
