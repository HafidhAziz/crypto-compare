package com.example.crypto_compare.module

import com.ashokvarma.gander.GanderInterceptor
import com.example.crypto_compare.BuildConfig
import com.example.crypto_compare.repository.WatchlistService
import com.example.framework.ResponseHandler
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = BuildConfig.BASE_URL
private const val TIMEOUT: Long = 60
val networkModule = module {
    single { GsonBuilder().create() }

    single {
        OkHttpClient.Builder().apply {
            connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            readTimeout(TIMEOUT, TimeUnit.SECONDS)
            cache(null)
            addInterceptor(GanderInterceptor(androidContext()).showNotification(true))
            addInterceptor { chain ->
                val request = requestBuilder(chain)
                chain.proceed(request)
            }
        }.build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
    }

    factory { get<Retrofit>().create(WatchlistService::class.java) }
    factory { ResponseHandler() }
}

private fun requestBuilder(
    chain: Interceptor.Chain
): Request {
    val original = chain.request()
    val apiKey = BuildConfig.API_KEY
    return original.newBuilder()
        .header("Authorization", "Apikey $apiKey")
        .build()
}