package com.release.domain.repositories

import com.release.domain.model.InspectionQuizItem

interface AuthRepository {

    suspend fun login(email: String, password: String)

    suspend fun logout()

    suspend fun getUserEnteredKey(): Boolean

    suspend fun getSavedInspectionsQuiz(): List<InspectionQuizItem>

    suspend fun startInspection(): List<InspectionQuizItem>

    suspend fun submitInspection(inspectionQuizItem: List<InspectionQuizItem>)

    suspend fun updateSavedInspectionQuiz(id: Int): Boolean
}
