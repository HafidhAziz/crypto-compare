package com.example.crypto_compare.presentation.watchlist

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.crypto_compare.data.DataItem
import com.example.crypto_compare.repository.WatchlistPagingSource
import com.example.crypto_compare.repository.WatchlistRepository
import com.example.framework.BaseViewModel

class WatchlistViewModel(private val watchlistRepository: WatchlistRepository) : BaseViewModel() {

    private var pagingDataSource: WatchlistPagingSource? = null
    val pagingWatchlist: LiveData<PagingData<DataItem>> by lazy {
        Pager(
            config = PagingConfig(
                pageSize = WatchlistPagingSource.PAGE_SIZE,
                prefetchDistance = WatchlistPagingSource.PREFETCH_DISTANCE
            ),
            pagingSourceFactory = {
                WatchlistPagingSource(watchlistRepository).also {
                    pagingDataSource = it
                }
            }
        ).liveData
    }

    fun getWatchlist() {
        pagingDataSource?.invalidate()
    }
}