package com.example.ergoen.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ergoen.data.db.entity.DbToken
import com.example.ergoen.data.db.dao.AuthDao
import com.example.ergoen.data.db.dao.ChargerDao
import com.example.ergoen.data.db.entity.DbCharger
import com.example.ergoen.data.db.entity.DbLocationDetails

const val DATA_BASE_VERSION = 1

@Database(
    entities = [DbToken::class, DbCharger::class, DbLocationDetails::class],
    version = DATA_BASE_VERSION,
    exportSchema = false
)
abstract class ErgoenDb : RoomDatabase() {
    abstract fun authDao(): AuthDao
    abstract fun chargerDao(): ChargerDao
}