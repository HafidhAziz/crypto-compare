package com.example.crypto_compare.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.crypto_compare.application.CryptoCompareApplication
import com.example.crypto_compare.preference.PreferenceProvider
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val preferenceModule = module {
    single { provideSettingsPreferences(androidApplication()) }
    single { PreferenceProvider(get()) }
}

private val PREFERENCES_FILE_KEY = CryptoCompareApplication::class.java.simpleName

private fun provideSettingsPreferences(app: Application): SharedPreferences =
    app.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)