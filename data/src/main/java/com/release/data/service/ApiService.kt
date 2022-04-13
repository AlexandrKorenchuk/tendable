package com.release.data.service

import com.release.data.model.LoginBody
import com.release.data.model.StartResponse
import com.release.data.model.SubmitBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun login(
        @Body body: LoginBody
    )

    @GET("inspections/start")
    suspend fun start(): StartResponse

    @POST("inspections/submit")
    suspend fun submit(
        @Body body: SubmitBody
    )
}
