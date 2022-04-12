package com.release.domain.usecase.inspection

import com.release.domain.model.InspectionQuizItem
import com.release.domain.repositories.AuthRepository
import com.release.domain.usecase.UseCase
import javax.inject.Inject

class SubmitInspectionUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<Unit, SubmitInspectionUseCase.Params>() {

    override suspend fun execute(params: Params) {
        authRepository.submitInspection(params.inspectionQuizItem)
    }

    data class Params(val inspectionQuizItem: List<InspectionQuizItem>)
}