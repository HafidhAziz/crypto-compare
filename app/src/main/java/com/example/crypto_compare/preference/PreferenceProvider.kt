package com.example.crypto_compare.preference

import android.content.SharedPreferences

class PreferenceProvider(private val preference: SharedPreferences) {

    companion object {
        private const val KEY_USER_NAME = "USER_NAME"
    }

    fun clearPreferences() {
        preference.edit().clear().apply()
    }

    fun setUsernamePreferences(username: String?) {
        username ?: return
        preference.edit().putString(KEY_USER_NAME, username).apply()
    }

    fun getUsernamePreferences(): String? {
        return preference.getString(KEY_USER_NAME, "")
    }
}
