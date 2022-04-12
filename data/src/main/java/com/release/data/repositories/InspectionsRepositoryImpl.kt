package com.release.data.repositories

import com.release.data.model.StartResponse
import com.release.data.model.SubmitBody
import com.release.data.service.ApiService
import com.release.data.utils.SafeApiCall
import com.release.data.utils.mapper.NetworkUiMapper
import com.release.domain.model.InspectionItem
import com.release.domain.repositories.InspectionsRepository
import javax.inject.Inject

class InspectionsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val inspectionMapper: NetworkUiMapper<StartResponse, InspectionItem>,
    private val safeApiCall: SafeApiCall
) : InspectionsRepository {

    override suspend fun getSavedInspectionsQuiz(): List<InspectionItem> {
        return emptyList()
        // if you return emty list
        //it means that no data stored in database
        //so you will have start button ans start method call
        //after clicking on it
        //if database not empty you will have
        //submit button.
        //so at first make start method.
        //   return listOf(InspectionQuizItem(1, "Clinic", "Emergency", "Write", "2/10"))
    }

    override suspend fun startInspection(): List<InspectionItem> {
        val response = safeApiCall.apiCall { apiService.start() }
        //TODO save whole quiz in database
        return listOf(inspectionMapper.mapNetworkToUi(response))
    }

    override suspend fun submitInspection(inspectionItem: List<InspectionItem>) {
        val body = SubmitBody(inspectionItem)
        safeApiCall.apiCall { apiService.submit(body) }
    }

    override suspend fun updateSavedInspectionQuiz(id: Int): Boolean {
        //TODO update quiz in db
        return true
    }
}