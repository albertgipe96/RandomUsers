package com.development.feature.randomusers.impl.domain.usecases

import com.development.feature.randomusers.impl.domain.model.RandomUser
import com.development.feature.randomusers.impl.domain.repository.RandomUsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetRandomUser(
    private val repository: RandomUsersRepository
) {
    suspend operator fun invoke(id: String): Result<RandomUser> {
        return withContext(Dispatchers.IO) {
            repository.getRandomUser(id)
        }
    }
}