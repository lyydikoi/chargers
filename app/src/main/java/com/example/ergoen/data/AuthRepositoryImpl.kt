package com.example.ergoen.data

import com.example.ergoen.data.db.dao.AuthDao
import com.example.ergoen.data.db.mapper.DbMapper
import com.example.ergoen.data.network.client.ErgoenApiClient
import com.example.ergoen.data.network.mapper.ApiMapper
import com.example.ergoen.data.network.model.LoginRequest
import com.example.ergoen.data.utils.RequestResult
import com.example.ergoen.domain.model.Token
import com.example.ergoen.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.lang.Exception

class AuthRepositoryImpl(
    private val authDao: AuthDao,
    private val dbMapper: DbMapper,
    private val apiClient: ErgoenApiClient,
    private val apiMapper: ApiMapper,
    private val dispatcherIO: CoroutineDispatcher
) : AuthRepository {
    override suspend fun login(email: String, password: String): RequestResult<Token> =
        withContext(dispatcherIO) {
            var token = Token.EMPTY

            return@withContext try {
                token = apiMapper.mapLoginResponseToDomain(
                    apiClient.login(
                        LoginRequest(
                            email = email,
                            code = password
                        )
                    )
                )

                RequestResult.Success(token)
            } catch (error: Throwable) {
                RequestResult.Error(
                    InvalidTokenException(error.message ?: error.toString())
                )
            } finally {
                authDao.updateToken(dbMapper.mapDomainTokenToDb(token.copy()))
            }
        }

    override suspend fun updateToken(token: Token) = withContext(dispatcherIO) {
        authDao.updateToken(dbMapper.mapDomainTokenToDb(token))
    }

    override fun getTokenStream(): Flow<Token> =
        authDao
            .getTokenStream()
            .distinctUntilChanged()
            .map { dbMapper.mapDbTokenToDomain(it) }

}

class InvalidTokenException(message: String) : Exception(message)
