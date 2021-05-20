package com.example.ergoen.domain.repository

interface AuthRepository {
    suspend fun login(username: String, password: String)
    suspend fun logout()
    suspend fun getToken(): String
    suspend fun setToken(token: String)
}