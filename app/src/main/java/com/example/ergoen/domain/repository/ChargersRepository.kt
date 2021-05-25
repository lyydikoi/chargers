package com.example.ergoen.domain.repository

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.room.Transaction
import com.example.ergoen.data.utils.RequestResult
import com.example.ergoen.domain.model.Charger
import com.example.ergoen.domain.model.LocationDetails
import kotlinx.coroutines.flow.Flow

interface ChargersRepository {
    fun getChargersStream(): Flow<List<Charger>>
    suspend fun getChargers(/*location: Location*/) : RequestResult<List<Charger>>

    suspend fun updateLocationDetails(locationDetails: LocationDetails)
    fun getLocationDetailsStream(): Flow<LocationDetails>
}
