package com.example.ergoen.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auth_table")
data class DbToken(
    @PrimaryKey
    var accessToken : String,
    var tokenType : String,
    var expiresIn : Int,
    var token : String
)
