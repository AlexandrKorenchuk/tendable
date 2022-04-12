package com.release.core_ui.utilis

import android.graphics.drawable.Drawable

sealed class ShowDialog {

    data class ExceptionDialog(
        val content: String,
    ) : ShowDialog()

    data class SignOutDialog(
        val contentView: Int,
        val backgroundDrawable: Drawable,
        val windowAnimation: Int,
        val closeDialog: Int,
        val signOutDialog: Int
    ) : ShowDialog()
}
