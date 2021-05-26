package com.example.ergoen.ui.chargers

import android.location.Location
import com.example.ergoen.domain.model.Charger
import com.example.ergoen.ui.chargers.ChargersListUiContract.ChargerViewState
import com.google.android.gms.maps.model.LatLng
import java.util.*

const val AMOUNT_OF_CHARGERS_TO_SHOW = 10

fun mapChargersToViewState(
    currentLocation: LatLng?,
    chargers: List<Charger>,
    isKwEnabled: Boolean,
    isDistanceEnabled: Boolean
): MutableList<ChargerViewState> {
    return chargers
        .map {
            mapChargerToViewState(
                charger = it,
                currentLocation = currentLocation,
                isKwEnabled = isKwEnabled,
                isDistanceEnabled = isDistanceEnabled
            )
        }
        .sortedBy { it.distance }
        .take(AMOUNT_OF_CHARGERS_TO_SHOW)
        .toMutableList()
}

private fun mapChargerToViewState(
    charger: Charger,
    currentLocation: LatLng?,
    isKwEnabled: Boolean,
    isDistanceEnabled: Boolean
): ChargerViewState {
    return with(charger) {
        val location = LatLng(latitude, longitude)
        ChargerViewState(
            locationName = name,
            distance = getDistanceBetweenInKm(
                startLatitude = currentLocation?.latitude,
                startLongitude = currentLocation?.longitude,
                endLatitude = location.latitude,
                endLongitude = location.longitude
            ),
            address = address,
            city = city,
            kw = evses.firstOrNull()?.connectors?.firstOrNull()?.maxKw.toString(),
            distanceLabelEnabled = isDistanceEnabled,
            kwLabelEnabled = isKwEnabled
        )
    }
}

// TODO: move to UTILS
private fun getDistanceBetweenInKm(
    startLatitude: Double?,
    startLongitude: Double?,
    endLatitude: Double?,
    endLongitude: Double?
): Float {
    if (startLatitude == null || startLongitude == null ||
        endLatitude == null || endLongitude == null
    ) return 0.0F

    val distanceBetween = FloatArray(1)
    if (startLatitude != 0.0 && startLongitude != 0.0) {
        Location.distanceBetween(
            startLatitude,
            startLongitude,
            endLatitude,
            endLongitude,
            distanceBetween
        )
        distanceBetween[0] = (distanceBetween[0] / 1000).roundTo(1)
    } else {
        distanceBetween[0] = 0.0F
    }
    return distanceBetween[0]
}

// TODO: move to UTILS
private fun Float.roundTo(decimalPlaces: Int): Float {
    return "%.${decimalPlaces}f".format(Locale.ENGLISH, this).toFloat()
}
