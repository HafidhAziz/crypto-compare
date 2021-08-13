package com.example.crypto_compare.repository

import com.example.crypto_compare.data.WatchlistResponse
import com.example.framework.Resource

interface WatchlistRepository {

    @Throws(Exception::class)
    suspend fun getWatchlist(page: Int, limit: Int): Resource<WatchlistResponse>
}