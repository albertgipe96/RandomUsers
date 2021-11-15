package com.albert.randomusers.di

import android.content.Context
import com.albert.randomusers.BaseApplication
import com.albert.randomusers.data.service.api.RandomUserApi
import com.albert.randomusers.data.service.RandomUserServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

}