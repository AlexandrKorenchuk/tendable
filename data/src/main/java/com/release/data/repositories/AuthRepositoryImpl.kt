package com.release.data.repositories

import com.release.data.model.LoginBody
import com.release.data.prefs.AppPrefs
import com.release.data.service.ApiService
import com.release.data.utils.SafeApiCall
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
            apiService.login(body)
            appPrefs.setEnteredKey(true)
        }
    }

    override suspend fun logout() {
        appPrefs.setEnteredKey(false)
    }

    override suspend fun getUserEnteredKey(): Boolean {
        return appPrefs.getEnteredKey()
    }
}
