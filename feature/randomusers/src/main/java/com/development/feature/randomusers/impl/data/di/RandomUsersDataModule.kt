@file:OptIn(ExperimentalPagingApi::class)

package com.development.feature.randomusers.impl.data.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.RemoteMediator
import com.development.core.storage.impl.model.RandomUserEntity
import com.development.feature.randomusers.impl.data.datasource.KeyDataSource
import com.development.feature.randomusers.impl.data.datasource.LocalRandomUsersDataSource
import com.development.feature.randomusers.impl.data.datasource.RemoteRandomUsersDataSource
import com.development.feature.randomusers.impl.data.datasource.RetrofitRemoteRandomUsersDataSource
import com.development.feature.randomusers.impl.data.datasource.RoomKeyDataSource
import com.development.feature.randomusers.impl.data.datasource.RoomLocalRandomUsersDataSource
import com.development.feature.randomusers.impl.data.mapper.DataToDomainMapper
import com.development.feature.randomusers.impl.data.repository.OfflineFirstRandomUsersRepository
import com.development.feature.randomusers.impl.data.repository.RandomUsersRemoteMediator
import com.development.feature.randomusers.impl.domain.repository.RandomUsersRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val randomUsersDataModule = module {
    factoryOf(::RoomLocalRandomUsersDataSource).bind<LocalRandomUsersDataSource>()
    factoryOf(::RetrofitRemoteRandomUsersDataSource).bind<RemoteRandomUsersDataSource>()
    factoryOf(::RoomKeyDataSource).bind<KeyDataSource>()
    factoryOf(::RandomUsersRemoteMediator).bind<RemoteMediator<Int, RandomUserEntity>>()
    factoryOf(::DataToDomainMapper)
    factoryOf(::OfflineFirstRandomUsersRepository).bind<RandomUsersRepository>()
}