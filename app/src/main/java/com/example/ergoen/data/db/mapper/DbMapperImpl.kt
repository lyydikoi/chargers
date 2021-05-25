package com.example.ergoen.data.db.mapper

import com.example.ergoen.data.db.entity.*
import com.example.ergoen.domain.model.*

class DbMapperImpl : DbMapper {
    override fun mapDomainTokenToDb(tokenArg: Token): DbToken {
        with(tokenArg) {
            return DbToken(
                accessToken,
                tokenType,
                expiresIn,
                token
            )
        }
    }

    override fun mapDbTokenToDomain(tokenArg: DbToken): Token {
        with(tokenArg) {
            return Token(
                accessToken,
                tokenType,
                expiresIn,
                token
            )
        }
    }

    override fun mapDomainChargerToDb(charger: Charger): DbCharger {
        return with(charger) {
            DbCharger(
                id,
                address,
                city,
                country,
                evses.map { mapDomainEvseToDb(it) },
                icon,
                isPrivate,
                isRemoved,
                latitude,
                longitude,
                name,
                provider
            )
        }
    }

    override fun mapDbChargerToDomain(charger: DbCharger): Charger {
        return with(charger) {
            Charger(
                address,
                city,
                country,
                evses.map { mapDbEvseToDomain(it) },
                icon,
                id,
                isPrivate,
                isRemoved,
                latitude,
                longitude,
                name,
                provider
            )
        }
    }

    override fun mapDbEvseToDomain(evse: DbEvse): Evse {
        return with(evse) {
            Evse(
                connectors.map { mapDbConnectorToDomain(it) },
                groupName,
                id
            )
        }
    }

    override fun mapDomainEvseToDb(evse: Evse): DbEvse {
        return with(evse) {
            DbEvse(
                connectors.map { mapDomainConnectorToDb(it) },
                groupName,
                id
            )
        }
    }

    override fun mapDbConnectorToDomain(connector: DbConnector): Connector {
        return with(connector) {
            Connector(
                maxKw,
                type
            )
        }
    }

    override fun mapDomainConnectorToDb(connector: Connector): DbConnector {
        return with(connector) {
            DbConnector(
                maxKw,
                type
            )
        }
    }

    override fun mapDomainLocationDetailsToDb(locationDetails: LocationDetails): DbLocationDetails {
        return with(locationDetails) {
            DbLocationDetails(
                lat,
                lng
            )
        }
    }

    override fun mapDbLocationDetailsToDomain(locationDetails: DbLocationDetails): LocationDetails {
        return with(locationDetails) {
            LocationDetails(
                lat,
                lng
            )
        }
    }
}
