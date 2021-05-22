package com.example.ergoen.data.network.client

import com.example.ergoen.data.network.model.LoginRequest
import com.example.ergoen.data.network.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ErgoenApiClient {
    @Headers( "Content-Type: application/json" )
    @POST("/v4/auth")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
}
