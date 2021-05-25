package com.example.ergoen.data.db.dao

import androidx.room.*
import com.example.ergoen.data.db.entity.DbToken
import kotlinx.coroutines.flow.Flow

@Dao
interface AuthDao {
    @Transaction
    suspend fun updateToken(token: DbToken) {
        clearToken()
        insert(token)
    }

    @Query("SELECT * from auth_table LIMIT(1)")
    fun getTokenStream(): Flow<DbToken>

    @Query("SELECT * from auth_table LIMIT(1)")
    fun getToken(): DbToken

    @Query("DELETE FROM auth_table")
    suspend fun clearToken()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(token: DbToken)
}
