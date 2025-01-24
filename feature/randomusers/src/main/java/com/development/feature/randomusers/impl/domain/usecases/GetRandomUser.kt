package com.development.feature.randomusers.impl.domain.usecases

import com.development.feature.randomusers.impl.domain.model.RandomUser
import com.development.feature.randomusers.impl.domain.repository.RandomUsersRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetRandomUser(
    private val repository: RandomUsersRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(id: String): Result<RandomUser> {
        return withContext(coroutineDispatcher) {
            repository.getRandomUser(id)
        }
    }
}