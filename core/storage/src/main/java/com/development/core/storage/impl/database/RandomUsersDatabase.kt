package com.development.core.storage.impl.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.development.core.storage.api.dao.RandomUserDao
import com.development.core.storage.api.dao.RemoteKeyDao
import com.development.core.storage.impl.model.RandomUserEntity
import com.development.core.storage.impl.model.RemoteKeyEntity

@Database(
    entities = [
        RandomUserEntity::class,
        RemoteKeyEntity::class
               ],
    version = 1
)
internal abstract class RandomUsersDatabase : RoomDatabase() {
    abstract val randomUserDao: RandomUserDao
    abstract val remoteKeyDao: RemoteKeyDao
}