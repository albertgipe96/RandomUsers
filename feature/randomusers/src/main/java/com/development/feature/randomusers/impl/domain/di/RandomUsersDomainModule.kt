package com.development.feature.randomusers.impl.domain.di

import com.development.feature.randomusers.impl.domain.usecases.DeleteRandomUser
import com.development.feature.randomusers.impl.domain.usecases.GetRandomUser
import com.development.feature.randomusers.impl.domain.usecases.GetRandomUsersList
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val randomUsersDomainModule = module {
    factoryOf(::GetRandomUsersList)
    factoryOf(::GetRandomUser)
    factoryOf(::DeleteRandomUser)
}