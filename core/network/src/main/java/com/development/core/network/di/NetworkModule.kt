package com.development.core.network.di

import com.development.core.network.RetrofitFactory
import com.development.core.network.service.RandomUsersApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    single {
        RetrofitFactory().build()
    }
    single {
        get<Retrofit>().create(RandomUsersApiService::class.java)
    }
}