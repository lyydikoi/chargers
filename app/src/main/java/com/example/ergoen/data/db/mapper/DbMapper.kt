package com.example.ergoen.data.db.mapper

import com.example.ergoen.data.db.entity.*
import com.example.ergoen.domain.model.*

interface DbMapper {
    fun mapDomainTokenToDb(tokenArg: Token): DbToken
    fun mapDbTokenToDomain(tokenArg: DbToken): Token

    fun mapDomainChargerToDb(charger: Charger): DbCharger
    fun mapDbChargerToDomain(charger: DbCharger): Charger
    fun mapDbEvseToDomain(evse: DbEvse): Evse
    fun mapDomainEvseToDb(evse: Evse): DbEvse
    fun mapDbConnectorToDomain(connector: DbConnector): Connector
    fun mapDomainConnectorToDb(connector: Connector): DbConnector

    fun mapDomainLocationDetailsToDb(locationDetails: LocationDetails): DbLocationDetails
    fun mapDbLocationDetailsToDomain(locationDetails: DbLocationDetails): LocationDetails
}
