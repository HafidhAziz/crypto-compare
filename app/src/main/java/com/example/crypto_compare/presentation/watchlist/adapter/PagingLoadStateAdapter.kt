package com.example.crypto_compare.presentation.watchlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.crypto_compare.R
import kotlinx.android.synthetic.main.layout_paging_load_state.view.*

class PagingLoadStateAdapter(
    private val onRetryClicked: () -> Unit
) : LoadStateAdapter<PagingLoadStateAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_paging_load_state,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.renderLoading(loadState is LoadState.Loading)
        if (loadState is LoadState.Error)
            holder.renderError(onRetryClicked)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun renderLoading(isShow: Boolean) = with(itemView) {
            if (isShow) {
                pbLoading.visibility = View.VISIBLE
                clGroupErrorState.visibility = View.GONE
            } else {
                pbLoading.visibility = View.GONE
            }
        }

        fun renderError(retryListener: () -> Unit) = with(itemView) {
            btnRetry.setOnClickListener {
                retryListener()
            }
        }
    }
}