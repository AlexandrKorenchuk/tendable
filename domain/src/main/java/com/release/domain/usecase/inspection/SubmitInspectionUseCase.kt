package com.release.domain.usecase.inspection

import com.release.domain.model.InspectionItem
import com.release.domain.repositories.AuthRepository
import com.release.domain.repositories.InspectionsRepository
import com.release.domain.usecase.UseCase
import javax.inject.Inject

class SubmitInspectionUseCase @Inject constructor(
    private val inspectionsRepository: InspectionsRepository
) : UseCase<Unit, SubmitInspectionUseCase.Params>() {

    override suspend fun execute(params: Params) {
        inspectionsRepository.submitInspection(params.inspectionItem)
    }

    data class Params(val inspectionItem: List<InspectionItem>)
}