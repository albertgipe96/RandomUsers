package com.development.feature.randomusers.impl.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.RemoteMediator
import androidx.paging.map
import com.development.core.storage.impl.model.RandomUserEntity
import com.development.feature.randomusers.impl.data.datasource.LocalRandomUsersDataSource
import com.development.feature.randomusers.impl.data.datasource.RemoteRandomUsersDataSource
import com.development.feature.randomusers.impl.data.mapper.DataToDomainMapper
import com.development.feature.randomusers.impl.domain.model.RandomUser
import com.development.feature.randomusers.impl.domain.repository.RandomUsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
class OfflineFirstRandomUsersRepository(
    private val localRandomUsersDataSource: LocalRandomUsersDataSource,
    private val remoteMediator: RemoteMediator<Int, RandomUserEntity>,
    private val mapper: DataToDomainMapper
) : RandomUsersRepository {

    override fun getRandomUsers(): Flow<PagingData<RandomUser>> = with(mapper) {
        return Pager(
            config = PagingConfig(pageSize = 15, prefetchDistance = 5),
            remoteMediator = remoteMediator,
            pagingSourceFactory = { localRandomUsersDataSource.getRandomUsers() }
        ).flow.map { pagingData -> pagingData.map { it.toDomain() } }
    }

    override fun deleteRandomUser(id: String) {

    }
}