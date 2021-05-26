package com.example.ergoen.data.network.client

import com.example.ergoen.data.network.model.ChargerResponseItem
import com.example.ergoen.data.network.model.LoginRequest
import com.example.ergoen.data.network.model.LoginResponse
import retrofit2.http.*

interface ErgoenApiClient {
    @Headers("Content-Type: application/json")
    @POST("/v4/auth")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @GET("/v4/stations/")
    suspend fun getChargers(
        @Query("latMin") latMin: Int,
        @Query("latMax") latMax: Int,
        @Query("longMin") longMin: Int,
        @Query("longMax") longMax: Int
    ): List<ChargerResponseItem>
}
