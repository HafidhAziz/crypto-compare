package com.example.crypto_compare.repository

import com.example.crypto_compare.data.WatchlistResponse
import com.example.framework.Resource
import com.example.framework.ResponseHandler

class WatchlistRepositoryImpl(
    private val service: WatchlistService,
    private val responseHandler: ResponseHandler
) : WatchlistRepository {

    override suspend fun getWatchlist(page: Int, limit: Int): Resource<WatchlistResponse> {
        try {
            val request = service.getWatchlistApi(page, limit)
            request.let {
                return responseHandler.setSuccess(it)
            }
        } catch (e: Exception) {
            return responseHandler.setException(e)
        }
    }
}