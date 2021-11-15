package com.albert.randomusers.di

import android.content.Context
import com.albert.randomusers.business.mapper.RandomUserMapper
import com.albert.randomusers.business.usecases.RandomUserInteractor
import com.albert.randomusers.business.usecases.RandomUserInteractorImpl
import com.albert.randomusers.data.mapper.RandomUserDtoMapper
import com.albert.randomusers.data.service.RandomUserService
import com.albert.randomusers.data.service.RandomUserServiceImpl
import com.albert.randomusers.data.service.api.RandomUserApi
import com.albert.randomusers.datapersistance.DataPersistance
import com.albert.randomusers.datapersistance.DataPersistanceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object PresentationModule {

    @Singleton
    @Provides
    fun provideRandomUserInteractor(
        randomUserService: RandomUserService,
        randomUserMapper: RandomUserMapper
    ): RandomUserInteractor {
        return RandomUserInteractorImpl(randomUserService, randomUserMapper)
    }

    @Singleton
    @Provides
    fun provideDataPersistance(
        @ApplicationContext app: Context
    ): DataPersistance {
        return DataPersistanceImpl(app)
    }

}