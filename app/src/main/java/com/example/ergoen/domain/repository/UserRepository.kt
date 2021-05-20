package com.example.ergoen.domain.repository

import com.example.ergoen.domain.model.User

interface UserRepository {
    suspend fun saveUser(user: User)
    suspend fun getUser(): User
    suspend fun cleanUser()
}
