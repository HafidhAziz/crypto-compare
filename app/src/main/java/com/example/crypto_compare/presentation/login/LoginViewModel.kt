package com.example.crypto_compare.presentation.login

import androidx.lifecycle.MutableLiveData
import com.example.crypto_compare.preference.PreferenceProvider
import com.example.framework.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val preference: PreferenceProvider) : BaseViewModel() {

    val usernameLogin = MutableLiveData<String?>()

    init {
        getUsernameFromLocal()
    }

    private fun getUsernameFromLocal() {
        launch {
            usernameLogin.value = preference.getUsernamePreferences()
        }
    }

    fun saveUsernameToLocal(username: String) {
        launch {
            withContext(Dispatchers.IO) {
                preference.setUsernamePreferences(username)
            }
        }
    }
}