package com.development.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteRandomUsers(
    val results: List<RemoteRandomUser>
)

@Serializable
data class RemoteRandomUser(
    val gender: String,
    val name: RemoteRandomUserName,
    val location: RemoteRandomUserLocation,
    val email: String,
    val registered: RemoteRandomUserRegistration,
    val phone: String,
    val picture: RemoteRandomUserPicture,
    val login: RemoteRandomUserLogin
)

@Serializable
data class RemoteRandomUserName(
    val first: String,
    val last: String
)

@Serializable
data class RemoteRandomUserLocation(
    val street: RemoteRandomUserLocationStreet,
    val city: String,
    val state: String
)

@Serializable
data class RemoteRandomUserLocationStreet(
    val name: String,
    val number: Int
)

@Serializable
data class RemoteRandomUserRegistration(
    val date: String
)

@Serializable
data class RemoteRandomUserPicture(
    val medium: String
)

@Serializable
data class RemoteRandomUserLogin(
    val uuid: String
)