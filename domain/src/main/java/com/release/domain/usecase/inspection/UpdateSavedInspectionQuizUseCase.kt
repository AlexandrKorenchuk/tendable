package com.release.domain.usecase.inspection

import com.release.domain.model.InspectionQuizItem
import com.release.domain.repositories.AuthRepository
import com.release.domain.usecase.None
import com.release.domain.usecase.UseCase
import javax.inject.Inject

class UpdateSavedInspectionQuizUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<Boolean, UpdateSavedInspectionQuizUseCase.Params>() {

    override suspend fun execute(params: Params): Boolean{
        return authRepository.updateSavedInspectionQuiz(params.id)
    }

    data class Params(val id: Int)
}