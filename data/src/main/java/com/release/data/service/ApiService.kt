package com.release.data.service

import com.release.data.model.AuthResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("")
    suspend fun login(
        @Query("name")
        name: String,
        @Query("password")
        password: String
    ): AuthResponse

    @POST("")
    suspend fun getInspections(
        @Query("id")
        id: String
    ): AuthResponse
}
