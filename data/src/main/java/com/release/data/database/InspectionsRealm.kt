package com.release.data.database

import com.release.data.model.Inspection
import com.release.domain.model.InspectionItem

interface InspectionsRealm {
    suspend fun getInspections(): List<InspectionItem>
    suspend fun insertInspection(inspection: Inspection)
}
