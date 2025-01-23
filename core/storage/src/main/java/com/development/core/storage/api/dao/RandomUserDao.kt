package com.development.core.storage.api.dao

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.development.core.storage.impl.model.RandomUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RandomUserDao {

    @Query("SELECT * FROM randomUsers")
    fun getRandomUsersList(): PagingSource<Int, RandomUserEntity>

    @Query("SELECT * FROM randomUsers WHERE id=:id")
    suspend fun getRandomUserById(id: Int): List<RandomUserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(randomUsers: List<RandomUserEntity>)

    @Query("DELETE FROM randomUsers")
    suspend fun clearAll()

    @Query("DELETE FROM randomUsers WHERE id=:id")
    suspend fun deleteRandomUser(id: Int)

}