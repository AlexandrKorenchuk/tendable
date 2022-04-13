package com.example.presentation.questions

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.release.core_ui.presentation.BaseViewModel
import com.release.domain.model.InspectionItem
import com.release.domain.usecase.inspection.GetQuestionsUseCase
import com.release.domain.utils.AppException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    val getQuestionsUseCase: GetQuestionsUseCase
) : BaseViewModel() {

    private val _items = MutableLiveData<List<InspectionItem>>()
    val items: LiveData<List<InspectionItem>>
        get() = _items

    init {
        viewModelScope.launch(handler) {
            try {
                val questions = getQuestionsUseCase.execute(GetQuestionsUseCase.Params(18))
                //TODO set questions
//                _items.value = questionsuestions
            } catch (e: AppException) {
                catchUseCaseException(e)
            }
        }
    }
}