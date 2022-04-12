package com.release.core_ui.utilis

sealed class SnackbarCustom {

    data class Error(
        val error: String
    ) : SnackbarCustom()

    data class Success(
        val success: String
    ) : SnackbarCustom()
}
