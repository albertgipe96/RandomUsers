package com.development.feature.randomusers.impl.presentation.model

data class RandomUserDetailUi(
    val id: String,
    val gender: String,
    val fullName: String,
    val locationString: String,
    val formattedRegisteredDate: String,
    val email: String,
    val picture: String
)