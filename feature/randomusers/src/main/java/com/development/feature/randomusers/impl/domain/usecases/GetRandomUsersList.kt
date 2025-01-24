package com.development.feature.randomusers.impl.domain.usecases

import androidx.paging.PagingData
import androidx.paging.filter
import com.development.feature.randomusers.impl.domain.model.RandomUser
import com.development.feature.randomusers.impl.domain.repository.RandomUsersRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class GetRandomUsersList(
    private val randomUsersRepository: RandomUsersRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): Flow<PagingData<RandomUser>> {
        return withContext(coroutineDispatcher) {
            val deletedIds = randomUsersRepository.getDeletedIds()
            randomUsersRepository.getRandomUsers()
                .map { it.filter { it.id !in deletedIds } } // Filter by deleted ones
                .flowOn(coroutineDispatcher)
        }
    }
}