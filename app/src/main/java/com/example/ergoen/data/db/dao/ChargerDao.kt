package com.example.ergoen.data.db.dao

import androidx.room.*
import com.example.ergoen.data.db.entity.DbCharger
import com.example.ergoen.data.db.entity.DbLocationDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface ChargerDao {
    @Transaction
    suspend fun updateLocationDetails(locationDetails: DbLocationDetails) {
        clearLocationDetails()
        insertLocationDetails(locationDetails)
    }

    @Query("DELETE FROM location_details_table")
    suspend fun clearLocationDetails()

    @Insert
    suspend fun insertLocationDetails(locationDetails: DbLocationDetails)

    @Query("SELECT * FROM location_details_table LIMIT(1)")
    fun getLocationDetailsStream(): Flow<DbLocationDetails>

    @Query("SELECT * FROM charger_table")
    fun getChargersStream(): Flow<List<DbCharger>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChargers(charger: List<DbCharger>)
}
