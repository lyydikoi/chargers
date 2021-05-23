package com.example.ergoen.data.network.model

import com.google.gson.annotations.SerializedName

data class ChargerResponseItem(
    @SerializedName("address")
    val address: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("evses")
    val evses: List<EvseResponse>,
    @SerializedName("icon")
    val icon: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isPrivate")
    val isPrivate: Boolean,
    @SerializedName("isRemoved")
    val isRemoved: Boolean,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("provider")
    val provider: String
)

data class EvseResponse(
    @SerializedName("connectors")
    val connectors: List<ConnectorResponse>,
    @SerializedName("groupName")
    val groupName: String,
    @SerializedName("id")
    val id: Int
)

data class ConnectorResponse(
    @SerializedName("maxKw")
    val maxKw: Float,
    @SerializedName("type")
    val type: String
)
