package com.development.feature.randomusers.impl.data.datasource

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.development.core.storage.impl.model.RandomUserEntity
import com.development.feature.randomusers.impl.domain.model.RandomUser
import kotlinx.coroutines.flow.Flow

interface LocalRandomUsersDataSource {
    fun getRandomUsers(): PagingSource<Int, RandomUserEntity>
    suspend fun deleteRandomUser(id: Int)
    suspend fun deleteAll()
    suspend fun updateRandomUsers(list: List<RandomUser>)
}