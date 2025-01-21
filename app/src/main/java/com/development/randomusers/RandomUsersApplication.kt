package com.development.randomusers

import android.app.Application
import com.development.core.storage.api.di.storageModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RandomUsersApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@RandomUsersApplication)
            modules(
                storageModule
            )
        }
    }
}