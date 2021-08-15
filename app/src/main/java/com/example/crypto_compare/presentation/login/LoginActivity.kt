package com.example.crypto_compare.presentation.login

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crypto_compare.R
import com.example.crypto_compare.databinding.ActivityLoginBinding
import com.example.crypto_compare.presentation.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupEvents()
        observeUserPreference()
    }

    private fun setupEvents() {
        binding.apply {
            btnLogin.setOnClickListener {
                val username = etUsername.text.toString()
                val password = etPassword.text.toString()
                var isValid = true
                if (username.isEmpty() || password.isEmpty()) {
                    isValid = false
                    showNoYetImplementedToast(getString(R.string.text_error_login_field))
                }
                if (isValid) {
                    viewModel.saveUsernameToLocal(username)
                    MainActivity.startThisActivity(this@LoginActivity)
                    hideKeyboard(it)
                }
            }
            tvFb.setOnClickListener {
                showNoYetImplementedToast(getString(R.string.text_not_yet_implemented))
            }
            tvGoogle.setOnClickListener {
                showNoYetImplementedToast(getString(R.string.text_not_yet_implemented))
            }
            tvForgotPassword.setOnClickListener {
                showNoYetImplementedToast(getString(R.string.text_not_yet_implemented))
            }
            btnLoginWithFingerPrint.setOnClickListener {
                showNoYetImplementedToast(getString(R.string.text_not_yet_implemented))
            }
            tvRegister.setOnClickListener {
                showNoYetImplementedToast(getString(R.string.text_not_yet_implemented))
            }
        }
    }

    private fun observeUserPreference() {
        viewModel.usernameLogin.observe(this) { username ->
            if (!username.isNullOrEmpty()) {
                MainActivity.startThisActivity(this)
            }
        }
    }

    private fun showNoYetImplementedToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun hideKeyboard(view: View) {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
    }
}