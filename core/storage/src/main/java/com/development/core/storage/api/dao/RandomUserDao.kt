package com.development.core.storage.api.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.development.core.storage.impl.model.RandomUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RandomUserDao {

    @Query("SELECT * FROM randomUsers")
    fun getRandomUsersList(): Flow<List<RandomUserEntity>>

    @Query("SELECT * FROM randomUsers WHERE id=:id")
    suspend fun getRandomUserById(id: Int): List<RandomUserEntity>

    @Upsert
    suspend fun upsertRandomUsers(randomUsers: List<RandomUserEntity>)

    @Query("DELETE FROM randomUsers WHERE id=:id")
    suspend fun deleteRandomUsers(id: Int)

}