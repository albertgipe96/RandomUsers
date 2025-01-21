package com.development.core.storage.api.di

import androidx.room.Room
import com.development.core.storage.impl.database.RandomUsersDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val storageModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            RandomUsersDatabase::class.java,
            "randomusers.db"
        ).build()
    }
    single { get<RandomUsersDatabase>().randomUserDao }
}