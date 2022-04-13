package com.release.domain.usecase.inspection

import com.release.domain.model.QuestionItem
import com.release.domain.repositories.InspectionsRepository
import com.release.domain.usecase.UseCase
import javax.inject.Inject

class GetQuestionsUseCase @Inject constructor(
    private val inspectionsRepository: InspectionsRepository
) : UseCase<List<QuestionItem>, GetQuestionsUseCase.Params>() {

    data class Params(val inspectionId: Int)

    override suspend fun execute(params: Params): List<QuestionItem> {
        return inspectionsRepository.getQuestionsById(params.inspectionId)
    }
}