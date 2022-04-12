package com.example.tendable

import androidx.lifecycle.viewModelScope
import com.release.core_ui.presentation.BaseViewModel
import com.release.core_ui.utilis.Event
import com.example.core_ui.utilis.NavigationEvent
import com.release.domain.usecase.None
import com.release.domain.usecase.auth.IsUserLoggedInUseCase
import com.release.domain.utils.AppException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    val isUserLoggedInUseCase: IsUserLoggedInUseCase
) : BaseViewModel() {

    init {
        viewModelScope.launch(handler) {
            try {
                val isUserLoggedIn = isUserLoggedInUseCase.execute(None)
                if (!isUserLoggedIn) {
                    _navigationEvent.value =
                        Event(
                            NavigationEvent.SetDestinationAndGraph(
                                com.example.presentation.R.id.loginFragment,
                                com.example.presentation.R.navigation.auth_graph
                            )
                        )
                } else {
                    _navigationEvent.value =
                        Event(
                            NavigationEvent.SetDestinationAndGraph(
                                com.example.presentation.R.id.savedInspectionFragment,
                                com.example.presentation.R.navigation.main_graph
                            )
                        )
                }
            } catch (e: AppException) {
                catchUseCaseException(e)
            }
        }
    }
}