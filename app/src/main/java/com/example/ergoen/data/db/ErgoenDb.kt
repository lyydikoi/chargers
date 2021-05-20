package com.example.ergoen.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ergoen.data.db.entity.DbUser
import com.example.ergoen.data.db.dao.UserDao
import com.example.ergoen.ui.utils.DATA_BASE_VERSION

@Database(entities = [DbUser::class], version = DATA_BASE_VERSION, exportSchema = false)
abstract class ErgoenDb : RoomDatabase() {

    abstract fun birdDao(): UserDao

}