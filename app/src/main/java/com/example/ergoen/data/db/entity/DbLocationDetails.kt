package com.example.ergoen.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location_details_table")
data class DbLocationDetails(
    @PrimaryKey
    val lat: Double,
    val lng: Double
)