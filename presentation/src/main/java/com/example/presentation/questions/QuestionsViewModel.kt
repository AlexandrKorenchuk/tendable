package com.example.presentation.questions

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.core_ui.utilis.ShowDialog
import com.release.core_ui.presentation.BaseViewModel
import com.release.core_ui.utilis.Event
import com.release.domain.model.InspectionItem
import com.release.domain.model.QuestionItem
import com.release.domain.usecase.inspection.GetQuestionsUseCase
import com.release.domain.utils.AppException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val getQuestionsUseCase: GetQuestionsUseCase
) : BaseViewModel() {

    private val _items = MutableLiveData<List<QuestionItem>>()
    val items: LiveData<List<QuestionItem>>
        get() = _items

    init {
        val inspectionId: Int? = savedStateHandle.get<Int>("bundle")
        Log.w("inspectionId", inspectionId.toString())
        viewModelScope.launch(handler) {
            try {
                if (inspectionId != null) {
                    val questions =
                        getQuestionsUseCase.execute(GetQuestionsUseCase.Params(inspectionId))
                    _items.value = questions
                } else {
                    _showDialog.value =
                        Event(ShowDialog.ExceptionDialog("Couldn't show questions!"))
                }
            } catch (e: AppException) {
                catchUseCaseException(e)
            }
        }
    }
}