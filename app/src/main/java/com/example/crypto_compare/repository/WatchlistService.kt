package com.example.crypto_compare.repository

import com.example.crypto_compare.data.WatchlistResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WatchlistService {
    @GET("top/totaltoptiervolfull")
    suspend fun getWatchlistApi(
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query("tysm") tysm: String = "USD",
        @Query("ascending") ascending: Boolean = true
    ): WatchlistResponse
}