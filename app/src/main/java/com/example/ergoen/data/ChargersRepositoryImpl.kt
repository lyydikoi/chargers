package com.example.ergoen.data

import com.example.ergoen.data.db.dao.ChargerDao
import com.example.ergoen.data.db.mapper.DbMapper
import com.example.ergoen.data.network.client.ErgoenApiClient
import com.example.ergoen.data.network.mapper.ApiMapper
import com.example.ergoen.data.network.model.ChargerResponseItem
import com.example.ergoen.data.utils.RequestResult
import com.example.ergoen.domain.model.Charger
import com.example.ergoen.domain.model.LocationDetails
import com.example.ergoen.domain.repository.ChargersRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.withContext
import java.lang.Exception

class ChargersRepositoryImpl(
    private val apiClient: ErgoenApiClient,
    private val apiMapper: ApiMapper,
    private val chargerDao: ChargerDao,
    private val dbMapper: DbMapper,
    private val dispatcherIO: CoroutineDispatcher
) : ChargersRepository {
    override fun getChargersStream(): Flow<List<Charger>> =
        chargerDao.getChargersStream()
            .distinctUntilChanged()
            .map { chargersList ->
                chargersList.map { dbMapper.mapDbChargerToDomain(it) }
            }

    override suspend fun updateChargers(
        latMin: Int,
        latMax: Int,
        lngMin: Int,
        lngMax: Int
    ): RequestResult<List<Charger>> =
        withContext(dispatcherIO) {
            var chargers: List<ChargerResponseItem>
            return@withContext try {
                chargers = apiClient.getChargers(latMin, latMax, lngMin, lngMax)
                val mappedChargers = chargers.map {
                    apiMapper.mapChargerResponseToDomain(it)
                }

                chargerDao.insertChargers(
                    mappedChargers.map { dbMapper.mapDomainChargerToDb(it) }
                )

                RequestResult.Success(mappedChargers)
            } catch (error: Throwable) {
                RequestResult.Error(
                    LoadChargersException(error.message ?: error.toString())
                )
            }
        }

    override suspend fun updateLocationDetails(locationDetails: LocationDetails) {
        chargerDao.updateLocationDetails(dbMapper.mapDomainLocationDetailsToDb(locationDetails))
    }

    override fun getLocationDetailsStream(): Flow<LocationDetails> =
        chargerDao
            .getLocationDetailsStream()
            .distinctUntilChanged()
            .mapNotNull { it }
            .map { dbMapper.mapDbLocationDetailsToDomain(it) }
}

class LoadChargersException(message: String) : Exception(message)
