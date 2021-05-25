package com.example.ergoen.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auth_table")
data class DbToken(
    @PrimaryKey
    val accessToken : String,
    val tokenType : String,
    val expiresIn : Int,
    val token : String
)
