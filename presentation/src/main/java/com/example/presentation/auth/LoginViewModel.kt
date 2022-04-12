package com.example.presentation.auth

import androidx.lifecycle.viewModelScope
import com.example.presentation.R
import com.release.core_ui.presentation.BaseViewModel
import com.release.core_ui.utilis.Event
import com.example.core_ui.utilis.NavigationEvent
import com.release.domain.usecase.auth.LogInUseCase
import com.release.domain.utils.AppException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val logInUseCase: LogInUseCase
) : BaseViewModel() {

    fun onLoginButtonClicked(email: String, password: String) {
        viewModelScope.launch(handler) {
            try {
                logInUseCase.execute(LogInUseCase.Params(email, password))
                _navigationEvent.value =
                    Event(NavigationEvent.ReplaceGraph(R.navigation.main_graph))
            } catch (e: AppException) {
                catchUseCaseException(e)
            }
        }
    }
}