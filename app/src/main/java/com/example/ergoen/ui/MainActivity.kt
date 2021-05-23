package com.example.ergoen.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.Navigation.findNavController
import com.example.ergoen.R
import com.example.ergoen.data.network.interceptors.UnauthorizedInterceptor
import java.lang.Exception

class MainActivity : AppCompatActivity(), UnauthorizedInterceptor.UnauthorizedExceptionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        UnauthorizedInterceptor.addListener(this)
    }

    override fun onTokenInvalid(exception: Exception) {
        Log.v("TEST_INTERCEPTOR", "UnauthorizedException: $exception")
    }

}