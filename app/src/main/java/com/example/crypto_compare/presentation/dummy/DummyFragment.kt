package com.example.crypto_compare.presentation.dummy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.crypto_compare.databinding.FragmentDummyBinding
import com.example.crypto_compare.presentation.watchlist.adapter.WatchlistAdapter

class DummyFragment : Fragment() {
    private var _binding: FragmentDummyBinding? = null
    private val binding get() = _binding!!

    private var watchlistAdapter = WatchlistAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDummyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}