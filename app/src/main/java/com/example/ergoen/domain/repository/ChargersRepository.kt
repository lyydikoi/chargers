package com.example.ergoen.domain.repository

import androidx.lifecycle.LiveData
import com.example.ergoen.data.db.entity.DbCharger
import com.example.ergoen.data.utils.RequestResult
import com.example.ergoen.domain.model.Charger
import com.example.ergoen.domain.model.LocationDetails
import kotlinx.coroutines.flow.Flow

interface ChargersRepository {
    suspend fun updateChargers(
        latMin: Int,
        latMax: Int,
        lngMin: Int,
        lngMax: Int
    ) : RequestResult<List<Charger>>
    fun getChargersStream(): Flow<List<Charger>>

    suspend fun updateLocationDetails(locationDetails: LocationDetails)
    fun getLocationDetailsStream(): Flow<LocationDetails>
}
