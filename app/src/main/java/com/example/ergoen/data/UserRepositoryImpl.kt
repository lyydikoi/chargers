package com.example.ergoen.data

import com.example.ergoen.data.db.dao.UserDao
import com.example.ergoen.domain.model.User
import com.example.ergoen.domain.repository.UserRepository


class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override suspend fun saveUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(): User {
        TODO("Not yet implemented")
    }

    override suspend fun cleanUser() {
        TODO("Not yet implemented")
    }

}
