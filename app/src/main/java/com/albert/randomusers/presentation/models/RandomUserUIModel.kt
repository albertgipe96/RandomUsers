package com.albert.randomusers.presentation.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RandomUserUIModel(
    val id: String? = null,
    val name: RandomUserNameUIModel? = null,
    val email: String? = null,
    val pictureThumbnail: String? = null,
    val phone: String? = null,
    val gender: String? = null,
    val location: RandomUserLocationUIModel? = null,
    val registeredDate: String? = null
) : Parcelable

@Parcelize
data class RandomUserNameUIModel(
    val first: String? = null,
    val last: String? = null
) : Parcelable

@Parcelize
data class RandomUserLocationUIModel(
    val street: String? = null,
    val city: String? = null,
    val state: String? = null
) : Parcelable