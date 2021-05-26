package com.example.ergoen.data.network.mapper

import com.example.ergoen.data.network.model.ChargerResponseItem
import com.example.ergoen.data.network.model.ConnectorResponse
import com.example.ergoen.data.network.model.EvseResponse
import com.example.ergoen.data.network.model.LoginResponse
import com.example.ergoen.domain.model.Charger
import com.example.ergoen.domain.model.Connector
import com.example.ergoen.domain.model.Evse
import com.example.ergoen.domain.model.Token

class ApiMapperImpl : ApiMapper {
    override fun mapLoginResponseToDomain(loginResponse: LoginResponse): Token {
        with(loginResponse) {
            return Token(
                accessToken,
                tokenType,
                expiresIn,
                token
            )
        }
    }

    override fun mapChargerResponseToDomain(chargerResponse: ChargerResponseItem): Charger {
        with(chargerResponse) {
            return Charger(
                address,
                city,
                country,
                evses.map { mapEvseResponseToDomain(it) },
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

    override fun mapEvseResponseToDomain(evseResponse: EvseResponse): Evse {
        with(evseResponse) {
            return Evse(
                connectors.map { mapConnectorResponseToDomain(it) },
                groupName,
                id
            )
        }
    }

    override fun mapConnectorResponseToDomain(connectorResponse: ConnectorResponse): Connector {
        with(connectorResponse) {
            return Connector(
                maxKw,
                type
            )
        }
    }
}
