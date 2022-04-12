package com.release.data.repositories

import android.util.Log
import com.release.data.model.LoginBody
import com.release.data.model.SubmitBody
import com.release.data.prefs.AppPrefs
import com.release.data.service.ApiService
import com.release.data.utils.SafeApiCall
import com.release.domain.model.InspectionQuizItem
import com.release.domain.repositories.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val appPrefs: AppPrefs,
    private val safeApiCall: SafeApiCall
) : AuthRepository {

    override suspend fun login(email: String, password: String) {
        val body = LoginBody(email, password)
        safeApiCall.apiCall {
           // apiService.login(body)
            appPrefs.setEnteredKey(true)
        }
    }

    override suspend fun logout() {
        appPrefs.setEnteredKey(false)
    }

    override suspend fun getUserEnteredKey(): Boolean {
        return appPrefs.getEnteredKey()
    }

    override suspend fun getSavedInspectionsQuiz(): List<InspectionQuizItem> {
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

    override suspend fun startInspection(): List<InspectionQuizItem> {
       val response = safeApiCall.apiCall { apiService.start() }
        Log.w("repoimpl", response.inspection.survey.categories.questions.answerChoices.name)
        //TODO save whole quiz in database
        return listOf(InspectionQuizItem(1, "Is the drug trolly locked?"))
    }

    override suspend fun submitInspection(inspectionQuizItem: List<InspectionQuizItem>) {
        val body = SubmitBody(inspectionQuizItem)
        safeApiCall.apiCall { apiService.submit(body) }
    }

    override suspend fun updateSavedInspectionQuiz(id: Int): Boolean {
        //TODO update quiz in db
        return true
    }
}
