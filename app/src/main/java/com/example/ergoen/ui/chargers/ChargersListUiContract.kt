package com.example.ergoen.ui.chargers

interface ChargersListUiContract {
    sealed class UiState {
        data class Success(val chargers: List<ChargerViewState>): UiState()
        data class Failure(val message: String): UiState()
    }

    data class ChargerViewState(
        val locationName: String,
        val distance: Float,
        val address: String,
        val city: String,
        val kw: String,
        val distanceLabelEnabled: Boolean,
        val kwLabelEnabled: Boolean
    )
}
