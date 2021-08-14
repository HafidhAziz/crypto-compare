package com.example.crypto_compare.presentation.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.example.crypto_compare.R
import com.example.crypto_compare.data.DataItem
import com.example.crypto_compare.databinding.FragmentWatchlistBinding
import com.example.crypto_compare.presentation.watchlist.adapter.PagingLoadStateAdapter
import com.example.crypto_compare.presentation.watchlist.adapter.WatchlistAdapter
import kotlinx.android.synthetic.main.fragment_watchlist.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class WatchlistFragment : Fragment() {

    private var _binding: FragmentWatchlistBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WatchlistViewModel by viewModel()

    private var watchlistAdapter = WatchlistAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWatchlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupRv()
        observeWatchlist()
        observeLoadState()
    }

    private fun init() {
        viewModel.getWatchlist()
    }

    private fun setupView() {
        binding.srlRefresh.setOnRefreshListener {
            watchlistAdapter.refresh()
        }
    }

    private fun setupRv() {
        binding.rvWatchlist.adapter = watchlistAdapter.withLoadStateFooter(
            PagingLoadStateAdapter(
                onRetryClicked = { watchlistAdapter.retry() }
            )
        )
    }

    private fun observeWatchlist() {
        viewModel.pagingWatchlist.observe(viewLifecycleOwner) { watchlist ->
            setPagingData(watchlist)
        }
    }

    private fun setPagingData(dataItem: PagingData<DataItem>) {
        lifecycleScope.launch {
            watchlistAdapter.submitData(dataItem)
        }
    }

    private fun observeLoadState() {
        lifecycleScope.launch {
            watchlistAdapter.loadStateFlow.collectLatest { loadState ->
                val state = loadState.refresh
                renderLoading(state)
                renderRecyclerView(state)
                renderEmptyState(state)
                renderErrorState(state)
            }
        }
    }

    private fun renderLoading(loadState: LoadState) {
        when (loadState) {
            is LoadState.Loading -> {
                rvWatchlist.visibility = View.GONE
                pbLoading.visibility = if (srlRefresh.isRefreshing) View.GONE else View.VISIBLE
            }
            else -> {
                pbLoading.visibility = View.GONE
                rvWatchlist.visibility = View.VISIBLE
                srlRefresh.isRefreshing = false
            }
        }
    }

    private fun renderRecyclerView(loadState: LoadState) {
        if (loadState is LoadState.NotLoading && watchlistAdapter.itemCount > 0) {
            rvWatchlist.visibility = View.VISIBLE
        }
    }

    private fun renderEmptyState(loadState: LoadState) {
        if (loadState is LoadState.NotLoading) {
            setEmptyStateVisibility(watchlistAdapter.itemCount == 0)
        }
    }

    private fun renderErrorState(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            val errorMessage = loadState.error.message ?: getString(R.string.text_error)
            showToast(errorMessage)
            rvWatchlist.visibility = View.GONE
        }
    }

    private fun setEmptyStateVisibility(isVisible: Boolean) {
        viewEmptyState.visibility = if (isVisible) View.VISIBLE else View.GONE
        rvWatchlist.visibility = if (isVisible) View.GONE else View.VISIBLE
    }

    private fun showToast(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
    }
}