package com.example.presentation.questions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.release.core_ui.presentation.BaseViewModel
import com.release.domain.model.InspectionItem
import com.release.domain.usecase.None
import com.release.domain.usecase.inspection.GetSavedInspectionUseCase
import com.release.domain.utils.AppException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    val getSavedInspectionsUseCase: GetSavedInspectionUseCase
) : BaseViewModel() {

    private val _items = MutableLiveData<List<InspectionItem>>()
    val items: LiveData<List<InspectionItem>>
        get() = _items

    init {
        viewModelScope.launch(handler) {
            try {
                val inspectionQuizUseCase = getSavedInspectionsUseCase.execute(None)
                _items.value = inspectionQuizUseCase
            } catch (e: AppException) {
                catchUseCaseException(e)
            }
        }
    }
}