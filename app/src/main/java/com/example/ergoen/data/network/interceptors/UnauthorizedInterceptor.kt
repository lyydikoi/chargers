package com.example.ergoen.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.lang.Exception

class UnauthorizedInterceptor : Interceptor {

    companion object {
        private var listener: UnauthorizedExceptionListener? = null

        fun publish(exception: Exception) {
            listener?.onTokenInvalid(exception)
        }
        fun addListener(li: UnauthorizedExceptionListener) {
            listener = li
        }
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.code == 401) {
            publish(UnauthorizedException())
        }

        return response
    }

    interface UnauthorizedExceptionListener {
        fun onTokenInvalid(exception: Exception)
    }

    class UnauthorizedException : Exception()
}