package com.development.feature.randomusers.impl.data.datasource

import com.development.core.network.service.RandomUsersApiService
import com.development.feature.randomusers.impl.data.mapper.DataToDomainMapper
import com.development.feature.randomusers.impl.domain.model.RandomUser

class RetrofitRemoteRandomUsersDataSource(
    private val apiService: RandomUsersApiService,
    private val mapper: DataToDomainMapper
) : RemoteRandomUsersDataSource {
    override suspend fun getRandomUsers(page: Int, pageSize: Int): List<RandomUser> = with(mapper) {
        return apiService.getRandomUsers(page, pageSize, "abc")
            .results
            .map { it.toDomain() }
    }
}