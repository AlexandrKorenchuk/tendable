package com.release.data.repositories

import com.release.data.database.InspectionsRealm
import com.release.data.model.StartResponse
import com.release.data.model.SubmitBody
import com.release.data.service.ApiService
import com.release.data.utils.SafeApiCall
import com.release.data.utils.mapper.DataUiMapper
import com.release.domain.model.InspectionItem
import com.release.domain.repositories.InspectionsRepository
import javax.inject.Inject

class InspectionsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val inspectionMapper: DataUiMapper<StartResponse, InspectionItem>,
    private val safeApiCall: SafeApiCall,
    private val inspectionsRealm: InspectionsRealm
) : InspectionsRepository {

    override suspend fun getSavedInspectionsQuiz(): List<InspectionItem> {
        return inspectionsRealm.getInspections()
    }

    override suspend fun startInspection(): List<InspectionItem> {
        val response = safeApiCall.apiCall { apiService.start() }
        inspectionsRealm.insertInspection(response.inspection)
        return listOf(inspectionMapper.mapDataToUi(response))
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