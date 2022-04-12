package com.example.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core_ui.utilis.NavigationEvent
import com.release.core_ui.presentation.BaseViewModel
import com.release.core_ui.utilis.Event
import com.release.domain.model.InspectionQuizItem
import com.release.domain.usecase.None
import com.release.domain.usecase.inspection.GetInspectionQuizUseCase
import com.release.domain.utils.AppException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InspectionQuizViewModel @Inject constructor(
    val getInspectionQuizUseCase: GetInspectionQuizUseCase
) : BaseViewModel() {

    private val _items = MutableLiveData<List<InspectionQuizItem>>()
    val items: LiveData<List<InspectionQuizItem>>
        get() = _items

    init {
        viewModelScope.launch(handler) {
            try {
                val inspectionQuizUseCase = getInspectionQuizUseCase.execute(None)
                _items.value = inspectionQuizUseCase
            } catch (e: AppException) {
                catchUseCaseException(e)
            }
        }
    }
}