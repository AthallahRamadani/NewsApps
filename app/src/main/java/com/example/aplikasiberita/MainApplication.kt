package com.example.aplikasiberita

import android.app.Application
import com.example.aplikasiberita.di.apiModule
import com.example.aplikasiberita.di.repositoryModule
import com.example.aplikasiberita.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MainApplication)
            modules(
                listOf(
                    apiModule,
                    vmModule,
                    repositoryModule
                )
            )
        }
    }
}