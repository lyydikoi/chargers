package com.example.ergoen.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class DbUser(
    @PrimaryKey
    val id: String,
    val name: String
)
