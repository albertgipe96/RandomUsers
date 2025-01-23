package com.development.feature.randomusers.impl.data.datasource

import androidx.paging.PagingSource
import com.development.core.storage.impl.model.RandomUserEntity
import com.development.feature.randomusers.impl.domain.model.RandomUser

interface LocalRandomUsersDataSource {
    fun getRandomUsers(): PagingSource<Int, RandomUserEntity>
    fun getRandomUsersList(): List<RandomUser>
    suspend fun getRandomUser(id: String): RandomUser
    suspend fun deleteRandomUser(id: Int)
    suspend fun deleteAll()
    suspend fun updateRandomUsers(list: List<RandomUser>)
}