package com.albert.randomusers.di

import com.albert.randomusers.business.mapper.RandomUserMapper
import com.albert.randomusers.business.usecases.RandomUserInteractor
import com.albert.randomusers.business.usecases.RandomUserInteractorImpl
import com.albert.randomusers.data.mapper.RandomUserDtoMapper
import com.albert.randomusers.data.service.RandomUserService
import com.albert.randomusers.data.service.RandomUserServiceImpl
import com.albert.randomusers.data.service.api.RandomUserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object InteractorModule {

    @Singleton
    @Provides
    fun provideRandomUserService(
        randomUserApi: RandomUserApi,
        randomUserDtoMapper: RandomUserDtoMapper
    ): RandomUserService {
        return RandomUserServiceImpl(randomUserApi, randomUserDtoMapper)
    }

    @Singleton
    @Provides
    fun providesBusinessToUIMapper(): RandomUserMapper {
        return RandomUserMapper()
    }

}