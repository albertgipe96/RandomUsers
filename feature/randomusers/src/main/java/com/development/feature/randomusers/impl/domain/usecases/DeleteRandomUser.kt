package com.development.feature.randomusers.impl.domain.usecases

import com.development.feature.randomusers.impl.domain.repository.RandomUsersRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DeleteRandomUser(
    private val repository: RandomUsersRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(id: String): Result<Unit> {
        return withContext(coroutineDispatcher) {
            repository.deleteRandomUser(id)
        }
    }
}