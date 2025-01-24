package com.development.core.storage.impl.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("deleted_ids")
data class DeletedIdEntity(
    @PrimaryKey val value: String
)
