package com.example.crypto_compare.presentation.login

import com.example.crypto_compare.preference.PreferenceProvider
import com.example.framework.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val preference: PreferenceProvider) : BaseViewModel() {

    fun saveUsernameToLocal(username: String) {
        launch {
            withContext(Dispatchers.IO) {
                preference.setUsernamePreferences(username)
            }
        }
    }
}