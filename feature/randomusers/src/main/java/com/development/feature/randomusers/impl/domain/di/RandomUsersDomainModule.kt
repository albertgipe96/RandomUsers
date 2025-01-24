package com.development.feature.randomusers.impl.domain.di

import com.development.feature.randomusers.impl.domain.usecases.DeleteRandomUser
import com.development.feature.randomusers.impl.domain.usecases.GetRandomUser
import com.development.feature.randomusers.impl.domain.usecases.GetRandomUsersList
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val dispatcherIO = "IODispatcher"

val randomUsersDomainModule = module {
    factory { GetRandomUsersList(get(), get(named(dispatcherIO))) }
    factory { GetRandomUser(get(), get(named(dispatcherIO))) }
    factory { DeleteRandomUser(get(), get(named(dispatcherIO))) }
    single(named(dispatcherIO)) { Dispatchers.IO }
}