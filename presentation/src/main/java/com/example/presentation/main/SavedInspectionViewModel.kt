package com.example.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core_ui.utilis.NavigationEvent
import com.example.presentation.R
import com.release.core_ui.presentation.BaseViewModel
import com.release.core_ui.utilis.Event
import com.release.domain.model.InspectionItems
import com.release.domain.usecase.None
import com.release.domain.usecase.auth.LogOutUseCase
import com.release.domain.usecase.inspection.GetSavedInspectionUseCase
import com.release.domain.utils.AppException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedInspectionViewModel @Inject constructor(
    val logOutUseCase: LogOutUseCase,
    val inspectionsUseCaseGetSaved: GetSavedInspectionUseCase
) : BaseViewModel() {

    private val _items = MutableLiveData<List<InspectionItems>>()
    val items: LiveData<List<InspectionItems>>
        get() = _items

    init {
        viewModelScope.launch(handler) {
            try {
                val inspectionsUseCase = inspectionsUseCaseGetSaved.execute(None)
                _items.value = inspectionsUseCase
            } catch (e: AppException) {
                catchUseCaseException(e)
            }
        }
    }

    fun onStartButtonClicked() {
        _navigationEvent.value =
            Event(NavigationEvent.Forward(R.id.action_savedInspectionFragment_to_inspectionQuizFragment))
    }

    fun onSubmitButtonClicked() {
        //TODO here must be server called and data sent and not displaying new screen.
        // it's just for now
        _navigationEvent.value =
            Event(NavigationEvent.Forward(R.id.action_savedInspectionFragment_to_inspectionQuizFragment))

    }

    fun onContinueButtonClicked(id: Int) {
        //TODO here get data from database by id
        // it's just for now
        _navigationEvent.value =
            Event(NavigationEvent.Forward(R.id.action_savedInspectionFragment_to_inspectionQuizFragment))
    }

    fun onLogoutButtonClicked() {
        viewModelScope.launch(handler) {
            try {
                logOutUseCase.execute(None)
                _navigationEvent.value =
                    Event(NavigationEvent.ReplaceGraph(R.navigation.auth_graph))
            } catch (e: AppException) {
                catchUseCaseException(e)
            }
        }
    }
}