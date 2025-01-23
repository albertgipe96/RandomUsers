@file:OptIn(ExperimentalPagingApi::class)

package com.development.feature.randomusers.impl.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.development.core.storage.impl.model.RandomUserEntity
import com.development.feature.randomusers.impl.data.datasource.KeyDataSource
import com.development.feature.randomusers.impl.data.datasource.LocalRandomUsersDataSource
import com.development.feature.randomusers.impl.data.datasource.RemoteRandomUsersDataSource
import com.development.feature.randomusers.impl.data.model.RemoteKey
import com.development.feature.randomusers.impl.domain.model.RandomUser
import kotlin.coroutines.cancellation.CancellationException

class RandomUsersRemoteMediator(
    private val localDataSource: LocalRandomUsersDataSource,
    private val remoteDataSource: RemoteRandomUsersDataSource,
    private val keyDataSource: KeyDataSource
) : RemoteMediator<Int, RandomUserEntity>() {

    private val REMOTE_KEY_ID = "randomusers"

    override suspend fun load(loadType: LoadType, state: PagingState<Int, RandomUserEntity>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    // RETRIEVE NEXT OFFSET FROM DATABASE
                    val remoteKey = keyDataSource.getById(REMOTE_KEY_ID)
                    if (remoteKey == null || remoteKey.nextOffset == 0) // END OF PAGINATION REACHED
                        return MediatorResult.Success(endOfPaginationReached = true)
                    remoteKey.nextOffset
                }
            }

            // Load from local database
            val localRandomUsers = localDataSource.getRandomUsersList()
                .drop((page-1)*state.config.pageSize)
                .take(state.config.pageSize)
            if (localRandomUsers.size < state.config.pageSize) {
                // Load from api call
                val remoteRandomUsers = remoteDataSource.getRandomUsers(
                    page = page,
                    pageSize = state.config.pageSize
                )
                // Save results and next offset to database
                localDataSource.updateRandomUsers(remoteRandomUsers)
                keyDataSource.insert(RemoteKey(id = REMOTE_KEY_ID, nextOffset = page + 1))
                // Check if end of pagination reached (remote)
                MediatorResult.Success(endOfPaginationReached = remoteRandomUsers.size < state.config.pageSize)
            } else {
                // Check if end of pagination reached (local)
                MediatorResult.Success(endOfPaginationReached = localRandomUsers.size < state.config.pageSize)
            }

        } catch (e: Exception) {
            if (e is CancellationException) throw e
            MediatorResult.Error(e)
        }
    }

}