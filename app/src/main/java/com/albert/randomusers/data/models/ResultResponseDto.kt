package com.albert.randomusers.data.models

import com.google.gson.annotations.SerializedName

data class ResultResponseDto(
    @SerializedName("results")
    var results: List<RandomUserDto>? = null
)

data class RandomUserDto(
    @SerializedName("id")
    var id: IdDto? = null,

    @SerializedName("name")
    var name: RandomUserNameDto? = null,

    @SerializedName("email")
    var email: String? = null,

    @SerializedName("picture")
    var picture: RandomUserPictureDto? = null,

    @SerializedName("phone")
    var phone: String? = null,

    @SerializedName("gender")
    var gender: String? = null,

    @SerializedName("location")
    var location: RandomUserLocationDto? = null,

    @SerializedName("registered")
    var registered: RandomUserRegisteredDto? = null
)

data class IdDto(
    @SerializedName("value")
    var value: String? = null
)

data class RandomUserNameDto(
    @SerializedName("first")
    var first: String? = null,

    @SerializedName("last")
    var last: String? = null
)

data class RandomUserPictureDto(
    @SerializedName("thumbnail")
    var thumbnail: String? = null
)

data class RandomUserLocationDto(
    @SerializedName("street")
    var street: StreetDto? = null,

    @SerializedName("city")
    var city: String? = null,

    @SerializedName("state")
    var state: String? = null
)

data class StreetDto(
    @SerializedName("name")
    var streetName: String? = null
)

data class RandomUserRegisteredDto(
    @SerializedName("date")
    var date: String? = null
)