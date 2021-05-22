package com.example.ergoen.data.utils

import java.lang.Exception

/**
 * A generic class that holds a value of network request result.
 * @param <T>
 */
sealed class RequestResult<out R> {

    data class Success<out T>(val data: T) : RequestResult<T>()
    data class Error(val exception: Exception) : RequestResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[message=$exception]"
        }
    }
}

/**
 * `true` if [RequestResult] is of type [RequestResult.Success] and holds
 * non-null [RequestResult.Success.data].
 */
val RequestResult<*>.successed
    get() = this is RequestResult.Success && data != null
