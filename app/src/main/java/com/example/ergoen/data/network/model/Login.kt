package com.example.ergoen.data.network.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email") val email: String,
    @SerializedName("code")val code: String
)

data class LoginResponse(
    @SerializedName("access_token") var accessToken : String,
    @SerializedName("token_type") var tokenType : String,
    @SerializedName("expires_in") var expiresIn : Int,
    @SerializedName("token") var token : String
)