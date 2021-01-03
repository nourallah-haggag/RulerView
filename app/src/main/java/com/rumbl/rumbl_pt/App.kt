package com.rumbl.rumbl_pt

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.rumbl.rumbl_pt.bases.LocaleManager
import com.rumbl.rumbl_pt.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import org.koin.core.logger.Level

/**
 * Created by Mohamed Shalan on 4/19/20.
 */

class App : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleManager.getLocale(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        LocaleManager.getLocale(this)
        super.onConfigurationChanged(newConfig)
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            if (BuildConfig.DEBUG) androidLogger(Level.ERROR) else EmptyLogger()
            androidContext(this@App)
            modules(
                listOf(
                    networkModules,
                    othersModules,
                    schedulersModule,
                    viewmodelModules,
                    adapterModule,
                    imageLoadingModules,
                    serializationModule,
                    otherServices,
                    repoModule,
                    networkServiceModule,
                    scopesModule
                )
            )
        }
    }
}
