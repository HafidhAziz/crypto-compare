package com.example.crypto_compare.module

import com.example.crypto_compare.presentation.login.LoginViewModel
import com.example.crypto_compare.presentation.watchlist.WatchlistViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { WatchlistViewModel(get()) }
}
