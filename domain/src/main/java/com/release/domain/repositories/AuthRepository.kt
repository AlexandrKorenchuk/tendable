package com.release.domain.repositories

import com.release.domain.model.InspectionItems
import com.release.domain.model.InspectionQuizItem

interface AuthRepository {

    suspend fun login(email: String, password: String)

    suspend fun logout()

    suspend fun getUserEnteredKey(): Boolean

    suspend fun getSavedInspections(): List<InspectionItems>

    suspend fun getInspectionQuiz(): List<InspectionQuizItem>

    suspend fun saveInspectionQuiz(): List<InspectionQuizItem>
}
