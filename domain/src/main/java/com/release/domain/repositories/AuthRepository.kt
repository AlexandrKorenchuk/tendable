package com.release.domain.repositories

interface AuthRepository {

    suspend fun login(email: String, password: String)

    suspend fun logout()

    suspend fun getUserEnteredKey(): Boolean
}
