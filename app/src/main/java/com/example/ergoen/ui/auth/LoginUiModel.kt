package com.example.ergoen.ui.auth

interface LoginUiModel {
    sealed class UiState {
        object Loading : UiState()
        data class Failure(val message: String) : UiState()
        data class Success(val loggedIn: Boolean) : UiState()
        data class FormValidation(val isFormValid: Boolean) : UiState()
    }

    sealed class Action {
        object performLogin : Action()
    }
}
