package com.example.ergoen.ui

import androidx.lifecycle.viewModelScope
import com.example.ergoen.domain.model.Token
import com.example.ergoen.domain.repository.AuthRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    fun resetToken() {
        viewModelScope.launch {
            authRepository.updateToken(Token.EMPTY)
        }
    }
}
