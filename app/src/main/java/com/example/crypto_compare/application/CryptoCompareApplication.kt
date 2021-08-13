package com.example.crypto_compare.application

import android.app.Application
import com.example.crypto_compare.module.networkModule
import com.example.crypto_compare.module.repositoryModule
import com.example.crypto_compare.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CryptoCompareApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@CryptoCompareApplication)
            modules(
                listOf(
                    viewModelModule,
                    networkModule,
                    repositoryModule
                )
            )
        }
    }
}