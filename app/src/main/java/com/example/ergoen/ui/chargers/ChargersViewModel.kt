 package com.example.ergoen.ui.chargers

import androidx.lifecycle.*
import com.example.ergoen.domain.model.Charger
import com.example.ergoen.domain.model.LocationDetails
import com.example.ergoen.domain.repository.AuthRepository
import com.example.ergoen.domain.repository.ChargersRepository
import com.example.ergoen.ui.BaseViewModel
import com.example.ergoen.ui.chargers.ChargersListUiContract.ChargerViewState
import com.example.ergoen.ui.chargers.ChargersListUiContract.UiState
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

private const val LOCATION_DELAY_MILLIS = 1000L

@ExperimentalCoroutinesApi
class ChargersViewModel(
    private val chargersRepository: ChargersRepository,
    authRepository: AuthRepository
) : BaseViewModel() {
    private val _isKwEnabled = MutableStateFlow(true)
    private val _isDistanceEnabled = MutableStateFlow(true)
    private val _settings = _isDistanceEnabled.combine(_isKwEnabled) { dist, kw ->
        dist to kw
    }

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: Flow<UiState> = _uiState.combine(_settings) { uiModel, settings ->
        when (uiModel) {
            is UiState.Success -> {
                uiModel.copy(
                    chargers = uiModel.chargers.map {
                        it.copy(
                            distanceLabelEnabled = settings.first,
                            kwLabelEnabled = settings.second
                        )
                    }
                )
            }
            is UiState.Failure -> uiModel
            is UiState.Loading -> uiModel
        }
    }

    init {
        viewModelScope.launch {
            chargersRepository
                .getChargersStream()
                .mapLatest { chargers ->
                    mapToViewState(
                        chargers = chargers,
                        isDistanceEnabled = _isDistanceEnabled.value,
                        isKwEnabled = _isKwEnabled.value
                    )
                }
                .catch { e ->
                    _uiState.value = UiState.Failure(e.message ?: "Something vent wrong...")
                }
                .collect { chargers ->
                    _uiState.value = UiState.Success(chargers)
                }
        }
    }

    // Control expired token
    val accessTokenStream: LiveData<String> =
        authRepository
            .getTokenStream()
            .map {
                it.accessToken
            }
            .asLiveData()

    val locationDetailsStream: LiveData<LocationDetails> =
        chargersRepository
            .getLocationDetailsStream()
            .debounce(LOCATION_DELAY_MILLIS)
            .asLiveData()

    fun updateChargers(locationDetails: LocationDetails) {
        launchDataLoad {
            chargersRepository.updateChargers(
                locationDetails.lat.toInt(),
                locationDetails.lat.toInt() + 1,
                locationDetails.lng.toInt(),
                locationDetails.lng.toInt() + 1
            )
        }
    }

    fun setIsKwEnabled(enabled: Boolean) {
        _isKwEnabled.value = enabled
    }

    fun setIsDistanceEnabled(enabled: Boolean) {
        _isDistanceEnabled.value = enabled
    }

    private fun mapToViewState(
        chargers: List<Charger>,
        isDistanceEnabled: Boolean,
        isKwEnabled: Boolean
    ): MutableList<ChargerViewState> {
        return mapChargersToViewState(
            chargers = chargers,
            isDistanceEnabled = isDistanceEnabled,
            isKwEnabled = isKwEnabled,
            currentLocation = locationDetailsStream.value?.let {
                LatLng(it.lat, it.lng)
            }
        )
    }
}
