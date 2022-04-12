package com.release.data.repositories

import com.release.data.prefs.AppPrefs
import com.release.data.service.ApiService
import com.release.data.utils.SafeApiCall
import com.release.domain.model.InspectionItems
import com.release.domain.model.InspectionQuizItem
import com.release.domain.repositories.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val appPrefs: AppPrefs,
    private val safeApiCall: SafeApiCall
) : AuthRepository {

    override suspend fun login(password: String, userName: String) {
//        safeApiCall.apiCall {
//            apiService.login(
//                password,
//                userName
//            )
//        }
        appPrefs.setEnteredKey(true)
    }

    override suspend fun logout() {
        appPrefs.setEnteredKey(false)
    }

    override suspend fun getUserEnteredKey(): Boolean {
        return appPrefs.getEnteredKey()
    }

    override suspend fun getSavedInspections(): List<InspectionItems> {
        return listOf(InspectionItems(1, "Clinic", "Emergency", "Write", "2/10"))
    }

    override suspend fun getInspectionQuiz(): List<InspectionQuizItem> {
        //        safeApiCall.apiCall { from network
//            apiService.login(
//                password,
//                userName
//            )
//        }
        //save response in database
        return listOf(InspectionQuizItem(1, "Is the drug trolly locked?"))
    }

    override suspend fun saveInspectionQuiz(): List<InspectionQuizItem> {
        //update quiz in db
        return emptyList()
    }
}
