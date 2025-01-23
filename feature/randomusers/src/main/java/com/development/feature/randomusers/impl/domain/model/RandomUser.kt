package com.development.feature.randomusers.impl.domain.model


data class RandomUser(
    val id: String,
    val name: String,
    val surname: String,
    val email: String,
    val picture: String,
    val phone: String,
    val gender: String,
    val location: RandomUserLocation,
    val registeredDate: String
)

data class RandomUserLocation(
    val street: String,
    val city: String,
    val state: String
)