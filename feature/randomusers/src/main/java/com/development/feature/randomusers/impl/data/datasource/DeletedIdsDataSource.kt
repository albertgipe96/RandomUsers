package com.development.feature.randomusers.impl.data.datasource

interface DeletedIdsDataSource {
    suspend fun getDeletedIds(): List<String>
    suspend fun insertDeletedId(id: String)
}