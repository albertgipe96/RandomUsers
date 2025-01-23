package com.development.feature.randomusers.impl.data.datasource

import com.development.feature.randomusers.impl.data.model.RemoteKey

interface KeyDataSource {
    suspend fun getById(id: String): RemoteKey?
    suspend fun deleteById(id: String)
    suspend fun insert(item: RemoteKey)
}