package com.example.ergoen.data.db.mapper

import com.example.ergoen.data.db.entity.DbCharger
import com.example.ergoen.data.db.entity.DbUser
import com.example.ergoen.domain.model.Charger
import com.example.ergoen.domain.model.User

interface DbMapper {
    fun mapDomainUserToDb(user: User): DbUser
    fun mapDbUserToDomain(user: DbUser): User

    fun mapDomainChargerToDb(charger: Charger): DbCharger
    fun mapDbChargerToDomain(charger: DbCharger): Charger
}
