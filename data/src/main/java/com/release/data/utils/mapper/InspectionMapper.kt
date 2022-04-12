package com.release.data.utils.mapper

import com.release.data.model.StartResponse
import com.release.domain.model.InspectionItem
import javax.inject.Inject

class InspectionMapper @Inject constructor() : NetworkUiMapper<StartResponse, InspectionItem> {
    override fun mapNetworkToUi(networkEntity: StartResponse): InspectionItem =
        InspectionItem(
            id = networkEntity.inspection.id,
            area = networkEntity.inspection.area.name,
            type = networkEntity.inspection.inspectionType.name,
            access = networkEntity.inspection.inspectionType.access
        )
}