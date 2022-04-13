package com.release.data.database

import com.release.data.database.entity.InspectionsEntity
import com.release.data.database.entity.QuestionEntity
import com.release.data.model.Inspection
import com.release.data.utils.mapper.DataUiMapper
import com.release.domain.model.InspectionItem
import com.release.domain.model.QuestionItem
import io.realm.kotlin.executeTransactionAwait
import javax.inject.Inject

class InspectionsRealmImpl @Inject constructor(
    private val dataBase: TendableDatabase,
    private val inspectionMapperDB: DataUiMapper<Inspection, InspectionsEntity>,
    private val questionDbUiMapper: DataUiMapper<QuestionEntity, QuestionItem>,
    private val inspectionDbUiMapper: DataUiMapper<InspectionsEntity, InspectionItem>
) : InspectionsRealm {

    override suspend fun getInspectionItems(): List<InspectionItem> {
        val inspections = arrayListOf<InspectionItem>()
        dataBase.realm.executeTransactionAwait { realmTransaction ->
            inspections.addAll(
                realmTransaction.where(InspectionsEntity::class.java)
                    .findAll()
                    .map { inspectionDbUiMapper.mapDataToUi(it) }
            )
        }
        return inspections
    }

    override suspend fun getQuestions(inspectionId: Int): List<QuestionItem> {
        val questions = arrayListOf<QuestionItem>()
        dataBase.realm.executeTransactionAwait { realmTransaction ->
            questions.addAll(
                realmTransaction.where(InspectionsEntity::class.java)
                    .equalTo("id", inspectionId)
                    .findFirst()
                    ?.questions
                    ?.map {
                        questionDbUiMapper.mapDataToUi(it)
                    } ?: listOf()
            )
        }
        return questions
    }

    override suspend fun insertInspection(inspection: Inspection) {
        dataBase.realm.executeTransactionAwait { realmTransaction ->
            val entity = inspectionMapperDB.mapDataToUi(inspection)
            realmTransaction.insertOrUpdate(entity)
        }
    }

    override suspend fun updateInspection(questionId: Int, answerId: Int): Boolean {
        dataBase.realm.executeTransactionAwait { realmTransaction ->
            realmTransaction.where(QuestionEntity::class.java)
                .equalTo("id", questionId)
                .findFirst()?.apply {
                    selectedAnswerChoiceId = answerId
                }
        }
        return true
    }

    override suspend fun getInspection(inspectionId: Int): Inspection? {
        var inspection: Inspection? = null
        dataBase.realm.executeTransactionAwait { realmTransaction ->
            val inspectionsEntity = realmTransaction.where(InspectionsEntity::class.java)
                .equalTo("id", inspectionId)
                .findFirst()
            if (inspectionsEntity != null)
                inspection = inspectionMapperDB.mapUiToData(inspectionsEntity)
        }
        return inspection
    }
}