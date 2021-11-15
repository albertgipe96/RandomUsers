package com.albert.randomusers.business.models

data class RandomUserBusinessModel(
    val id: String? = null,
    val name: RandomUserNameBusinessModel? = null,
    val email: String? = null,
    val pictureThumbnail: String? = null,
    val phone: String? = null,
    val gender: String? = null,
    val location: RandomUserLocationBusinessModel? = null,
    val registeredDate: String? = null
)

data class RandomUserNameBusinessModel(
    val first: String? = null,
    val last: String? = null
)

data class RandomUserLocationBusinessModel(
    val street: String? = null,
    val city: String? = null,
    val state: String? = null
)