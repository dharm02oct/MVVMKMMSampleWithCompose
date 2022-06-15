package com.harman.mvvmkmmsample.android

import android.app.Application
import com.harman.mvvmkmmsample.ContextArgs
import com.harman.mvvmkmmsample.android.di.allModules
import com.harman.mvvmkmmsample.di.KoinInjector
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ContactsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            KoinInjector.provideContext(ContextArgs(this@ContactsApplication))
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@ContactsApplication)
            modules(allModules())
        }
    }
}