package com.release.domain.usecase.inspection

import com.release.domain.model.InspectionItems
import com.release.domain.repositories.AuthRepository
import com.release.domain.usecase.None
import com.release.domain.usecase.UseCase
import javax.inject.Inject

class GetSavedInspectionUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<List<InspectionItems>, None>() {

    override suspend fun execute(params: None) : List<InspectionItems> {
        return authRepository.getSavedInspections()
    }
}