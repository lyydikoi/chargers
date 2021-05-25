package com.example.ergoen.ui

import android.location.Location
import androidx.lifecycle.viewModelScope
import com.example.ergoen.domain.model.LocationDetails
import com.example.ergoen.domain.model.Token
import com.example.ergoen.domain.repository.AuthRepository
import com.example.ergoen.domain.repository.ChargersRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val authRepository: AuthRepository,
    private val chargersRepository: ChargersRepository
) : BaseViewModel() {

    fun resetToken() {
        viewModelScope.launch {
            authRepository.updateToken(Token.EMPTY)
        }
    }

    fun setUserLocation(location: Location) {
        viewModelScope.launch {
            chargersRepository.updateLocationDetails(
                LocationDetails(location.latitude, location.longitude)
            )
        }
    }
}
