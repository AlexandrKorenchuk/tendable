package com.release.data.repositories

import android.util.Log
import com.release.data.database.InspectionsRealm
import com.release.data.model.StartResponse
import com.release.data.model.SubmitBody
import com.release.data.service.ApiService
import com.release.data.utils.SafeApiCall
import com.release.data.utils.mapper.DataUiMapper
import com.release.domain.model.InspectionItem
import com.release.domain.model.QuestionItem
import com.release.domain.repositories.InspectionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InspectionsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val inspectionMapper: DataUiMapper<StartResponse, InspectionItem>,
    private val safeApiCall: SafeApiCall,
    private val inspectionsRealm: InspectionsRealm
) : InspectionsRepository {

    override suspend fun getSavedInspections(): List<InspectionItem> {
        return withContext(Dispatchers.IO) {
            inspectionsRealm.getInspectionItems()
        }
    }

    override suspend fun startInspection(): List<InspectionItem> {
        return withContext(Dispatchers.IO) {
            val response = safeApiCall.apiCall { apiService.start() }
            inspectionsRealm.insertInspection(response.inspection)
            Log.w("id", response.inspection.id.toString())
            listOf(inspectionMapper.mapDataToUi(response))
        }
    }

    override suspend fun submitInspection(inspectionId: Int) {
        withContext(Dispatchers.IO) {
            val inspectionDB = inspectionsRealm.getInspection(inspectionId)
            val body = SubmitBody(inspectionDB!!)
            safeApiCall.apiCall { apiService.submit(body) }
        }
    }

    override suspend fun updateQuestionAnswer(questionId: Int, answerId: Int): Boolean {
        return withContext(Dispatchers.IO) {
            inspectionsRealm.updateInspection(questionId, answerId)
        }
    }

    override suspend fun getQuestionsById(inspectionId: Int): List<QuestionItem> {
        return withContext(Dispatchers.IO) {
            inspectionsRealm.getQuestions(inspectionId)
        }
    }
}