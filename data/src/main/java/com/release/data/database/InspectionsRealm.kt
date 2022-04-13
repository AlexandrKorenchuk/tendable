package com.release.data.database

import com.release.data.model.Inspection
import com.release.domain.model.InspectionItem
import com.release.domain.model.QuestionItem

interface InspectionsRealm {
    suspend fun getInspections(): List<InspectionItem>
    suspend fun getQuestions(inspectionId: Int): List<QuestionItem>
    suspend fun insertInspection(inspection: Inspection)
}