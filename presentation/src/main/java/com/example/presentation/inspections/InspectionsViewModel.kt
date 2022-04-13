package com.example.presentation.inspections

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core_ui.utilis.NavigationEvent
import com.example.core_ui.utilis.ShowDialog
import com.example.presentation.R
import com.release.core_ui.presentation.BaseViewModel
import com.release.core_ui.utilis.Event
import com.release.domain.model.InspectionItem
import com.release.domain.usecase.None
import com.release.domain.usecase.auth.LogOutUseCase
import com.release.domain.usecase.inspection.GetSavedInspectionUseCase
import com.release.domain.usecase.inspection.StartInspectionUseCase
import com.release.domain.usecase.inspection.SubmitInspectionUseCase
import com.release.domain.utils.AppException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InspectionsViewModel @Inject constructor(
    val logOutUseCase: LogOutUseCase,
    val getSavedInspectionsUseCase: GetSavedInspectionUseCase,
    val submitInspectionUseCase: SubmitInspectionUseCase,
    val requestStartInspectionUseCase: StartInspectionUseCase
) : BaseViewModel() {

    private val _items = MutableLiveData<List<InspectionItem>>()
    val items: LiveData<List<InspectionItem>>
        get() = _items

    init {
        onGetSavedData()
    }

    private fun onGetSavedData() {
        viewModelScope.launch(handler) {
            try {
                val inspectionsUseCase = getSavedInspectionsUseCase.execute(None)
                _items.value = inspectionsUseCase
            } catch (e: AppException) {
                catchUseCaseException(e)
            }
        }
    }

    fun onStartButtonClicked() {
        viewModelScope.launch(handler) {
            try {
                requestStartInspectionUseCase.execute(None)
                _navigationEvent.value =
                    Event(NavigationEvent.Forward(R.id.action_savedInspectionFragment_to_inspectionQuizFragment))
            } catch (e: AppException) {
                catchException(e)
            }
        }
    }

    fun onSubmitButtonClicked() {
        viewModelScope.launch(handler) {
            try {
                val submitItems = items.value
                if (submitItems != null) {
                    submitInspectionUseCase.execute(SubmitInspectionUseCase.Params(submitItems))
                    _showDialog.value = Event(ShowDialog.SuccessDialog("successfully submitted"))
                }
            } catch (e: AppException) {
                catchException(e)
            }
        }
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