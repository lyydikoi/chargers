package com.example.ergoen.data.db.mapper

import com.example.ergoen.data.db.entity.DbCharger
import com.example.ergoen.data.db.entity.DbToken
import com.example.ergoen.domain.model.Charger
import com.example.ergoen.domain.model.Token
import com.example.ergoen.domain.model.User

interface DbMapper {
    fun mapDomainTokenToDb(tokenArg: Token): DbToken
    fun mapDbTokenToDomain(tokenArg: DbToken): Token

    /*fun mapDomainChargerToDb(charger: Charger): DbCharger
    fun mapDbChargerToDomain(charger: DbCharger): Charger*/
}
