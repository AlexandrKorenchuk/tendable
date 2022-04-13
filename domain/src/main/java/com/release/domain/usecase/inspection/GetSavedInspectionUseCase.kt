package com.release.domain.usecase.inspection

import com.release.domain.model.InspectionItem
import com.release.domain.repositories.InspectionsRepository
import com.release.domain.usecase.None
import com.release.domain.usecase.UseCase
import javax.inject.Inject

class GetSavedInspectionUseCase @Inject constructor(
    private val inspectionsRepository: InspectionsRepository
) : UseCase<List<InspectionItem>, None>() {

    override suspend fun execute(params: None) : List<InspectionItem> {
        return inspectionsRepository.getSavedInspections()
    }
}