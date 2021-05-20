package com.example.ergoen.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ergoen.data.db.entity.DbUser

@Dao
interface UserDao {
    @Query("SELECT * from user_table")
    fun getUser(): LiveData<List<DbUser>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: DbUser)

    @Delete
    suspend fun delete(user: DbUser)
}