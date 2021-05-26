package com.example.ergoen.data.db.entity

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "charger_table")
@TypeConverters(
    DbEvseListConverter::class,
    DbConnectorListConverter::class
)
class DbCharger(
    @PrimaryKey
    val id: Int,
    val address: String,
    val city: String,
    val country: String?,
    val evses: List<DbEvse>,
    val icon: Int,
    val isPrivate: Boolean,
    val isRemoved: Boolean,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val provider: String
)

data class DbEvse(
    val connectors: List<DbConnector>,
    val groupName: String,
    val id: Int
)

data class DbConnector(
    val maxKw: Float?,
    val type: String
)

class DbEvseListConverter {
    val gson = Gson()

    @TypeConverter
    fun fromList(dbEvses: List<DbEvse>): String {
        val type = object : TypeToken<List<DbEvse>>() {}.type
        return gson.toJson(dbEvses, type)
    }

    @TypeConverter
    fun fromString(serializedString: String): List<DbEvse> {
        val type = object : TypeToken<List<DbEvse>>() {}.type
        return gson.fromJson(serializedString, type)
    }
}

class DbConnectorListConverter {
    val gson = Gson()

    @TypeConverter
    fun fromList(dbConnectors: List<DbConnector>): String {
        val type = object : TypeToken<List<DbConnector>>() {}.type
        return gson.toJson(dbConnectors, type)
    }

    @TypeConverter
    fun fromString(serializedString: String): List<DbConnector> {
        val type = object : TypeToken<List<DbConnector>>() {}.type
        return gson.fromJson(serializedString, type)
    }
}
