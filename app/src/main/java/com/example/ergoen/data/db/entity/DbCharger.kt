package com.example.ergoen.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "charger_table")
class DbCharger(
    @PrimaryKey
    val id: String
)