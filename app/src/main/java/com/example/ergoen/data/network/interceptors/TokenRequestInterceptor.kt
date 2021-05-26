package com.example.ergoen.data.network.interceptors

import com.example.ergoen.data.db.dao.AuthDao
import com.example.ergoen.data.db.mapper.DbMapper
import com.example.ergoen.domain.model.Token
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

const val HEADER_AUTHORIZATION = "Authorization"
const val BEARER = "Bearer"

class TokenRequestInterceptor(
    private val authDao: AuthDao,
    private val dbMapper: DbMapper
) : Interceptor {
    private var token: Token? = null

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        token = authDao.getToken()?.let {
            dbMapper.mapDbTokenToDomain(it)
        } ?: Token.EMPTY

        val originalRequest = chain.request()
        var newRequest: Request? = null

        if (token!!.accessToken.isNotBlank()) {
            newRequest = originalRequest.newBuilder()
                .addHeader(HEADER_AUTHORIZATION, "$BEARER ${token!!.accessToken}")
                .build()
        }

        return if (newRequest != null) chain.proceed(newRequest)
        else chain.proceed(originalRequest)
    }
}
