package com.example.ergoen.data.network.mapper

import com.example.ergoen.data.network.model.ChargerResponseItem
import com.example.ergoen.data.network.model.ConnectorResponse
import com.example.ergoen.data.network.model.EvseResponse
import com.example.ergoen.data.network.model.LoginResponse
import com.example.ergoen.domain.model.Charger
import com.example.ergoen.domain.model.Connector
import com.example.ergoen.domain.model.Evse
import com.example.ergoen.domain.model.Token

interface ApiMapper {
    fun mapLoginResponseToDomain(loginResponse: LoginResponse): Token
    fun mapChargerResponseToDomain(chargerResponse: ChargerResponseItem): Charger
    fun mapEvseResponseToDomain(evseResponse: EvseResponse): Evse
    fun mapConnectorResponseToDomain(connectorResponse: ConnectorResponse): Connector
}
