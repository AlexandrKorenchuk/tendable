package com.release.data.utils.mapper

import com.release.data.model.StartResponse
import com.release.domain.model.InspectionItem
import javax.inject.Inject

class InspectionNetworkUIMapper @Inject constructor() : DataUiMapper<StartResponse, InspectionItem> {
    override fun mapDataToUi(dataEntity: StartResponse): InspectionItem =
        InspectionItem(
            id = dataEntity.inspection.id,
            area = dataEntity.inspection.area.name,
            type = dataEntity.inspection.inspectionType.name,
            access = dataEntity.inspection.inspectionType.access
        )
}