package com.development.feature.randomusers.impl.data.datasource

import com.development.core.storage.api.dao.DeletedIdsDao
import com.development.core.storage.impl.model.DeletedIdEntity

class RoomDeletedIdsDataSource(
    private val deletedIdsDao: DeletedIdsDao
) : DeletedIdsDataSource {

    override suspend fun getDeletedIds(): List<String> {
        return deletedIdsDao.getDeletedIds().map { it.value }
    }

    override suspend fun insertDeletedId(id: String) {
        deletedIdsDao.insertDeletedId(DeletedIdEntity(id))
    }

}