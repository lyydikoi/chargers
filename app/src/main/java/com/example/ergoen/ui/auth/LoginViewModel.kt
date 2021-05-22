package com.example.ergoen.ui.auth

import androidx.lifecycle.*
import com.example.ergoen.domain.model.Token
import com.example.ergoen.domain.repository.AuthRepository
import com.example.ergoen.ui.BaseViewModel
import com.example.ergoen.ui.auth.LoginUiModel.Action
import com.example.ergoen.ui.auth.LoginUiModel.UiState

class LoginViewModel(
    private val authRepository: AuthRepository
) : BaseViewModel() {
    private val _loginResult by lazy { MutableLiveData<Token>() }
    private val _username by lazy { MutableLiveData("") }
    private val _password by lazy { MutableLiveData("") }
    private val _uiState: MediatorLiveData<UiState> = prepareUIStateMediator()
    val uiState: LiveData<UiState>
        get() = _uiState

    private fun prepareUIStateMediator(): MediatorLiveData<UiState> {
        val result = MediatorLiveData<UiState>()

        result.addSource(_username) {
            result.value = UiState.FormValidation(isFormValid = checkValidation())
        }
        result.addSource(_password) {
            result.value = UiState.FormValidation(isFormValid = checkValidation())
        }
        result.addSource(loading) {
            result.value = UiState.Loading
        }
        result.addSource(errorMsg) {
            errorMsg.value?.let {
                result.value = UiState.Failure(it)
            }
        }
        result.addSource(_loginResult) {
            result.value = _loginResult.value?.let { token ->
                if (token.accessToken.isNotBlank()) UiState.Success(loggedIn = true)
                else UiState.Failure("")
            }
        }
        return result
    }

    fun handleAction(action: Action) {
        when(action) {
            is Action.performLogin -> login()
        }
    }

    private fun login() {
        if (_username.value.isNullOrBlank() || _password.value.isNullOrBlank()) return

        launchDataLoad(_loginResult) { 
            authRepository.login(_username.value!!, _password.value!!)
        }
    }

    private fun checkValidation(): Boolean {
        return !_username.value.isNullOrBlank() && !_password.value.isNullOrBlank()
    }

    fun onUsernameChanged(input: String) {
        _username.value = input
    }

    fun onPasswordChanged(input: String) {
        _password.value = input
    }
}
