package com.release.data.database.entity

//TODO add realm
open class InspectionsEntity(
    var id: Int,//add key
    var areaId: AreaEntity,
    var inspectionTypeId: InspectionTypeEntity,
    var questions: List<QuestionEntity>
)