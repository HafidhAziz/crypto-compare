package com.example.crypto_compare.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.crypto_compare.data.DataItem
import com.example.framework.NetworkState

class WatchlistPagingSource(private val watchlistRepo: WatchlistRepository) :
    PagingSource<Int, DataItem>() {

    private fun getPageOffset(pageIndex: Int) = pageIndex * PAGE_SIZE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        val page = params.key ?: 0
        val offset = getPageOffset(page)

        val response = watchlistRepo.getWatchlist(page = offset, limit = PAGE_SIZE)

        return when (response.networkState) {
            NetworkState.SUCCESS -> {
                val watchlist = response.data?.data ?: emptyList()
                val nextPage = page + 1
                val isAbleToLoadMore = watchlist.size == PAGE_SIZE
                LoadResult.Page(
                    data = watchlist,
                    prevKey = null, // prevKey = null, because paging move forward
                    nextKey = if (isAbleToLoadMore) nextPage else null
                )
            }
            NetworkState.ERROR -> LoadResult.Error(Exception(response.message))
            else -> {
                LoadResult.Error(Exception(response.message))
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        const val PAGE_SIZE = 10
        const val PREFETCH_DISTANCE = 9
    }
}