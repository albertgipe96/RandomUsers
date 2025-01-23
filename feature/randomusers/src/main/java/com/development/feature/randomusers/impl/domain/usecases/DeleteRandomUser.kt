package com.development.feature.randomusers.impl.domain.usecases

import com.development.feature.randomusers.impl.domain.repository.RandomUsersRepository

class DeleteRandomUser(
    private val repository: RandomUsersRepository
) {
    suspend operator fun invoke(id: String): Result<Unit> {
        return repository.deleteRandomUser(id)
    }
}