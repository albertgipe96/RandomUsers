package com.development.feature.randomusers.impl.data.datasource

import com.development.feature.randomusers.impl.domain.model.RandomUser

interface RemoteRandomUsersDataSource {
    suspend fun getRandomUsers(page: Int, pageSize: Int): List<RandomUser>
}