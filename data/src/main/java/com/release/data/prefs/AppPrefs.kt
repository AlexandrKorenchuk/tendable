package com.release.data.prefs

import android.content.Context
import javax.inject.Inject

class AppPrefs @Inject constructor(
    context: Context
) {

    private val prefsKey by lazy {
        context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
    }

    fun setEnteredKey(isEntered: Boolean) {
        prefsKey
            .edit()
            .putBoolean(ENTERED_KEY, isEntered)
            .apply()
    }

    fun getEnteredKey(): Boolean {
        return prefsKey.getBoolean(ENTERED_KEY, false)
    }

    companion object {
        private const val PREF_KEY = "PREF_KEY"
        private const val ENTERED_KEY = "ENTERED_KEY"
    }
}
