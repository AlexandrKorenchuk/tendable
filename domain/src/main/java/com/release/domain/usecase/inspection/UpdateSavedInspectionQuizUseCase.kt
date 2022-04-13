package com.release.domain.usecase.inspection

import com.release.domain.repositories.InspectionsRepository
import com.release.domain.usecase.UseCase
import javax.inject.Inject

class UpdateSavedInspectionQuizUseCase @Inject constructor(
    private val inspectionsRepository: InspectionsRepository
) : UseCase<Boolean, UpdateSavedInspectionQuizUseCase.Params>() {

    override suspend fun execute(params: Params): Boolean {
        return inspectionsRepository.updateQuestionAnswer(params.id)
    }

    data class Params(val id: Int)
}