package com.development.feature.randomusers.impl.domain.repository

import androidx.paging.PagingData
import com.development.feature.randomusers.impl.domain.model.RandomUser
import kotlinx.coroutines.flow.Flow

interface RandomUsersRepository {
    fun getRandomUsers(): Flow<PagingData<RandomUser>>
    suspend fun getRandomUser(id: String): Result<RandomUser>
    fun deleteRandomUser(id: String)
}