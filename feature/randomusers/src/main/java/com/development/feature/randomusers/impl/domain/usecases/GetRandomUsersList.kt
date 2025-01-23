package com.development.feature.randomusers.impl.domain.usecases

import androidx.paging.PagingData
import com.development.feature.randomusers.impl.domain.model.RandomUser
import com.development.feature.randomusers.impl.domain.repository.RandomUsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetRandomUsersList(
    private val randomUsersRepository: RandomUsersRepository
) {
    operator fun invoke(): Flow<PagingData<RandomUser>> {
        return randomUsersRepository.getRandomUsers() // TODO: Filter by deleted ones
            .flowOn(Dispatchers.IO)
    }
}