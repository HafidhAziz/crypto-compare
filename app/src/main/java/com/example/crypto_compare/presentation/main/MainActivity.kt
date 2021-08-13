package com.example.crypto_compare.presentation.main

import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.crypto_compare.R
import com.example.crypto_compare.databinding.ActivityMainBinding
import com.example.framework.setupWithNavController
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var mainBinding: ActivityMainBinding
    private var currentNavController: LiveData<NavController>? = null
    private var lastBackPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.nav_graph_watchlist,
            R.navigation.nav_graph_stream,
            R.navigation.nav_graph_search,
            R.navigation.nav_graph_chat,
            R.navigation.nav_graph_portfolio
        )

        val controller = mainBinding.bottomNav.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.navHostFragment,
            intent = intent
        )
        currentNavController = controller
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        event?.let {
            if (it.keyCode == KeyEvent.KEYCODE_BACK && it.action == KeyEvent.ACTION_DOWN) {
                if (it.downTime - lastBackPressedTime < BACK_PRESSED_PERIOD) {
                    finishAffinity()
                } else {
                    Toast.makeText(
                        this,
                        getString(R.string.back_pressed_info),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    lastBackPressedTime = it.eventTime
                }
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    companion object {
        private const val BACK_PRESSED_PERIOD: Long = 1000
    }
}