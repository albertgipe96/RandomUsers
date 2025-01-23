package com.development.core.storage.impl.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "randomUsers")
data class RandomUserEntity(
    @PrimaryKey val id: String,
    val name: String,
    val surname: String,
    val email: String,
    val picture: String,
    val phone: String,
    val gender: String,
    val street: String,
    val city: String,
    val state: String,
    val registeredDate: String
)