package com.example.core_ui.utilis

sealed class ShowDialog {

    data class ExceptionDialog(
        val content: String,
    ) : ShowDialog()

    data class SuccessDialog(
        val content: String,
    ) : ShowDialog()
}
