package com.mandiri.aplikasiberita

import android.app.Application
import com.mandiri.aplikasiberita.di.apiModule
import com.mandiri.aplikasiberita.di.repositoryModule
import com.mandiri.aplikasiberita.di.vmModule
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