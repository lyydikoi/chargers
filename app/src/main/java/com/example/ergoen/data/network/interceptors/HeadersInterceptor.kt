package com.example.ergoen.data.network.interceptors

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain
                .request()
                .apply {
                    Log.d("TAG","Test Headers ${this.headers.toString()}")
                    Log.d("TAG","Test Headers ${chain.toString()}")
                }
                .newBuilder()
                .build()
        )
    }
}