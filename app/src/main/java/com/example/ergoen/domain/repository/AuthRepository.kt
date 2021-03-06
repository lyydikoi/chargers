package com.example.ergoen.domain.repository

import com.example.ergoen.data.utils.RequestResult
import com.example.ergoen.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): RequestResult<Token>
    suspend fun updateToken(token: Token)
    fun getTokenStream(): Flow<Token>
}
