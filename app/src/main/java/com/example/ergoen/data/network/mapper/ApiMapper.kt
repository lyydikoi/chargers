package com.example.ergoen.data.network.mapper

import com.example.ergoen.data.network.model.LoginResponse
import com.example.ergoen.domain.model.Token

interface ApiMapper {
    fun mapLoginResponseToDomain(loginResponse: LoginResponse) : Token
}