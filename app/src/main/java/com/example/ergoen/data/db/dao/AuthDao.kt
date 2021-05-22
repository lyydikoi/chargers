package com.example.ergoen.data.db.dao

import androidx.room.*
import com.example.ergoen.data.db.entity.DbToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface AuthDao {
    fun getDistinctToken(): Flow<DbToken> = getToken().distinctUntilChanged()

    @Transaction
    suspend fun updateToken(token: DbToken) {
        clearToken()
        insert(token)
    }

    @Query("SELECT * from auth_table ")
    fun getToken(): Flow<DbToken>

    @Query("DELETE FROM auth_table")
    suspend fun clearToken()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(token: DbToken)
}
