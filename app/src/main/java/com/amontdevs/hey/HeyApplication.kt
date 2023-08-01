package com.amontdevs.hey

import android.app.Application
import com.amontdevs.hey.modules.daoModule
import com.amontdevs.hey.modules.repositoryModule
import com.amontdevs.hey.modules.sourceModule
import com.amontdevs.hey.modules.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class HeyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@HeyApplication)
            modules(
                sourceModule,
                daoModule,
                repositoryModule,
                viewModelsModule
            )
        }
    }
}