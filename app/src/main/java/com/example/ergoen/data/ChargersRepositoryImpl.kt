package com.example.ergoen.data

import android.location.Location
import androidx.lifecycle.LiveData
import com.example.ergoen.data.network.client.ErgoenApiClient
import com.example.ergoen.data.network.mapper.ApiMapper
import com.example.ergoen.data.utils.RequestResult
import com.example.ergoen.domain.model.Charger
import com.example.ergoen.domain.repository.ChargersRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.lang.Exception

class ChargersRepositoryImpl(
    private val apiClient: ErgoenApiClient,
    private val apiMapper: ApiMapper,
    private val dispatcherIO: CoroutineDispatcher
) : ChargersRepository {
    override fun closestChargers(): Flow<List<Charger>> {
        TODO("Not yet implemented")
    }

    override suspend fun getChargers(/*location: Location*/): RequestResult<List<Charger>> =
        withContext(dispatcherIO) {
            return@withContext try {
                // TODO: remove hardcode:
                RequestResult.Success(
                    apiClient.getChargers(60, 61, 24, 25).map {
                        apiMapper.mapChargerResponseToDomain(it)
                    }
                )
            } catch (error: Throwable) {
                RequestResult.Error(
                    LoadChargersException(error.message ?: error.toString())
                )
            }
        }
    }

class LoadChargersException(message: String) : Exception(message)
