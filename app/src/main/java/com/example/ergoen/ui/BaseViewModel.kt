package com.example.ergoen.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ergoen.data.utils.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

open class BaseViewModel : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _errorMsg by lazy { MutableLiveData<String>() }
    val errorMsg: LiveData<String>
        get() = _errorMsg

    private val _dataLoadException by lazy { MutableLiveData<Exception>() }
    val dataLoadException: LiveData<Exception>
        get() = _dataLoadException

    /**
     * Helper function to call data load function with a loading spinner and errors triggered in a snackbar.
     *
     * @param block lambda to actually load data.
     */
    fun <T> launchDataLoad(mutableLiveData: MutableLiveData<T>? = null, block: suspend () -> RequestResult<T>): Job {
        return viewModelScope.launch {
            _loading.value = true
            val result = try {
                withContext(Dispatchers.IO) {
                    block()
                }
            } catch (e: Exception) {
                RequestResult.Error(Exception(e.message ?: "Unknown data load error..."))
            } finally {
                _loading.value = false
            }

            handleResponse(result, mutableLiveData)
        }
    }

    fun <T> handleResponse(result: RequestResult<T>, mutableLiveData: MutableLiveData<T>? = null) {
        when (result) {
            is RequestResult.Success -> mutableLiveData?.postValue(result.data)
            is RequestResult.Error -> {
                _dataLoadException.postValue(result.exception)
                _errorMsg.postValue(result.exception.message)
            }
        }
    }
}