package com.bc

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class App : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseFirestore.setLoggingEnabled(true)
        configureDi()
    }

    open fun configureDi() =
        startKoin {
            androidContext(this@App)
            modules(provideComponent())
        }

    open fun provideComponent() = appComponent

}