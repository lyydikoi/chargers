package com.example.ergoen.domain.model

data class Charger(
    val address: String,
    val city: String,
    val country: String,
    val evses: List<Evse>,
    val icon: Int,
    val id: Int,
    val isPrivate: Boolean,
    val isRemoved: Boolean,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val provider: String
)

data class Evse(
    val connectors: List<Connector>,
    val groupName: String,
    val id: Int
)

data class Connector(
    val maxKw: Float,
    val type: String
)
