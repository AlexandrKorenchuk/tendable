package com.release.core_ui.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.release.core_ui.utilis.Event
import com.release.core_ui.utilis.NavigationEvent
import com.release.core_ui.utilis.ShowDialog
import com.release.core_ui.utilis.SnackbarCustom
import com.release.domain.utils.AppException
import com.release.domain.utils.NoInternetException
import kotlinx.coroutines.CoroutineExceptionHandler

open class BaseViewModel : ViewModel() {

    protected val _visibility = MutableLiveData<Int>()

    val visibility: LiveData<Int>
        get() = _visibility

    protected val _navigationEvent = MutableLiveData<Event<NavigationEvent>>()

    val navigationEvent: LiveData<Event<NavigationEvent>>
        get() = _navigationEvent

    protected val _snackBarCommand = MutableLiveData<Event<SnackbarCustom>>()

    val snackBarCommand: LiveData<Event<SnackbarCustom>>
        get() = _snackBarCommand

    protected val _showDialog = MutableLiveData<Event<ShowDialog>>()

    val showDialog: LiveData<Event<ShowDialog>>
        get() = _showDialog

    val handler = CoroutineExceptionHandler { _, exception ->
        Log.w("CoroutineException", exception.toString())
    }

    fun catchException(e: AppException) {
        when (e) {
            is NoInternetException -> _snackBarCommand.value =
                Event(SnackbarCustom.Error(e.message.toString()))
            else -> _snackBarCommand.value = Event(SnackbarCustom.Error(e.message.toString()))
        }
    }

    fun catchUseCaseException(e: Exception) {
        _showDialog.value = Event(ShowDialog.ExceptionDialog(e.message.toString()))
    }
}