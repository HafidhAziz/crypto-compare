package com.example.crypto_compare.presentation.watchlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.crypto_compare.R
import com.example.crypto_compare.data.DataItem
import kotlinx.android.synthetic.main.item_watchlist.view.*

class WatchlistAdapter :
    PagingDataAdapter<DataItem, WatchlistAdapter.ViewHolder>(ItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_watchlist, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(dataItem: DataItem?) = with(itemView) {
            tvCryptoCode.text = dataItem?.coinInfo?.name
            tvCryptoName.text = dataItem?.coinInfo?.fullName
            tvPriceNow.text = dataItem?.dISPLAY?.uSD?.pRICE
            tvPriceIncrement.text = dataItem?.dISPLAY?.uSD?.mKTCAPPENALTY
        }
    }

    class ItemComparator : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem) =
            oldItem.coinInfo?.id == newItem.coinInfo?.id

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return false
        }
    }
}