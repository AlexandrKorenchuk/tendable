package com.release.core_ui.utilis

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import javax.inject.Inject

open class ResourceManager @Inject constructor(
    private val context: Context
) {

    fun getString(@StringRes string: Int): String {
        return when (string != 0) {
            true -> context.getString(string)
            else -> ""
        }
    }

    fun getColor(@ColorRes color: Int): Int {
        return ContextCompat.getColor(context, color)
    }
}
