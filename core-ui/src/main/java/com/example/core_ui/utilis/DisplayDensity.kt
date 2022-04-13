package com.release.core_ui.utilis

import android.content.Context
import javax.inject.Inject

class DisplayDensity @Inject constructor(
    private val context: Context
) {

    fun toIntDp(int: Int): Int {
        return (int * context.resources.displayMetrics.density).toInt()
    }

    fun toFloatDp(float: Float): Float {
        return (float * (2.5f / context.resources.displayMetrics.density))
    }
}
