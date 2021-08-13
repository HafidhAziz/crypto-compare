package com.example.crypto_compare.module

import com.example.crypto_compare.repository.WatchlistRepository
import com.example.crypto_compare.repository.WatchlistRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<WatchlistRepository> { WatchlistRepositoryImpl(get(), get()) }
}