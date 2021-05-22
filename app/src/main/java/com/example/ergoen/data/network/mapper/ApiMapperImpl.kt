package com.example.ergoen.data.network.mapper

import com.example.ergoen.data.network.model.LoginResponse
import com.example.ergoen.domain.model.Token

class ApiMapperImpl : ApiMapper {
    override fun mapLoginResponseToDomain(loginResponse: LoginResponse): Token {
        with(loginResponse) {
            return Token(
                accessToken,
                tokenType,
                expiresIn,
                token
            )
        }
    }
}