package com.development.feature.randomusers.impl.data.datasource

import com.development.core.storage.api.dao.RemoteKeyDao
import com.development.core.storage.impl.model.RemoteKeyEntity
import com.development.feature.randomusers.impl.data.model.RemoteKey

class RoomKeyDataSource(
    private val remoteKeyDao: RemoteKeyDao
) : KeyDataSource {

    override suspend fun getById(id: String): RemoteKey? {
        return remoteKeyDao.getById(id)?.let { RemoteKey(it.id, it.nextOffset) }
    }

    override suspend fun deleteById(id: String) {
        remoteKeyDao.deleteById(id)
    }

    override suspend fun insert(item: RemoteKey) {
        remoteKeyDao.insert(RemoteKeyEntity(item.id, item.nextOffset))
    }
}