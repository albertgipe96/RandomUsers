package com.albert.randomusers.di

import com.albert.randomusers.data.mapper.RandomUserDtoMapper
import com.albert.randomusers.data.service.api.RandomUserApi
import com.albert.randomusers.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun provideRandomUserApi(): RandomUserApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(RandomUserApi::class.java)
    }

    @Singleton
    @Provides
    fun providesDtoToBusinessMapper(): RandomUserDtoMapper {
        return RandomUserDtoMapper()
    }

}