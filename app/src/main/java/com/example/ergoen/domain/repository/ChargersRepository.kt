package com.example.ergoen.domain.repository

import android.location.Location
import androidx.lifecycle.LiveData
import com.example.ergoen.data.utils.RequestResult
import com.example.ergoen.domain.model.Charger
import kotlinx.coroutines.flow.Flow

interface ChargersRepository {
    fun closestChargers(): Flow<List<Charger>>
    suspend fun getChargers(/*location: Location*/) : RequestResult<List<Charger>>
}
