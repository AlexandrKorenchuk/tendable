package com.release.domain.repositories

import com.release.domain.model.InspectionItem

interface InspectionsRepository {

    suspend fun getSavedInspectionsQuiz(): List<InspectionItem>

    suspend fun startInspection(): List<InspectionItem>

    suspend fun submitInspection(inspectionItem: List<InspectionItem>)

    suspend fun updateSavedInspectionQuiz(id: Int): Boolean
}