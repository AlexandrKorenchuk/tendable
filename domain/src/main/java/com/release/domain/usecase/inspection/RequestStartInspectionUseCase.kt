package com.release.domain.usecase.inspection

import com.release.domain.model.InspectionQuizItem
import com.release.domain.repositories.AuthRepository
import com.release.domain.usecase.None
import com.release.domain.usecase.UseCase
import javax.inject.Inject

class RequestStartInspectionUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<List<InspectionQuizItem>, None>() {

    override suspend fun execute(params: None): List<InspectionQuizItem> {
        return authRepository.startInspection()
    }
}