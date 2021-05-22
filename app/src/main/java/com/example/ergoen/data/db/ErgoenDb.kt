package com.example.ergoen.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ergoen.data.db.entity.DbToken
import com.example.ergoen.data.db.dao.AuthDao
import com.example.ergoen.utils.DATA_BASE_VERSION

@Database(entities = [DbToken::class], version = DATA_BASE_VERSION, exportSchema = false)
abstract class ErgoenDb : RoomDatabase() {
    abstract fun authDao(): AuthDao
}