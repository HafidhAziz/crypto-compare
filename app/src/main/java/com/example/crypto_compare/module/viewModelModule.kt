package com.example.crypto_compare.module

import com.example.crypto_compare.presentation.watchlist.WatchlistViewModel
import com.example.crypto_compare.presentation.login.LoginViewModel
import com.example.crypto_compare.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { LoginViewModel() }
    viewModel { WatchlistViewModel() }
}
