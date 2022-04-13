package com.release.data.utils.mapper

import com.release.data.database.entity.*
import com.release.data.model.Inspection
import io.realm.RealmList
import javax.inject.Inject

class InspectionNetworkDBMapper @Inject constructor() : DataUiMapper<Inspection, InspectionsEntity> {
    override fun mapDataToUi(dataEntity: Inspection): InspectionsEntity =
        InspectionsEntity(
            id = dataEntity.id,
            area = AreaEntity(
                id = dataEntity.area.id,
                name = dataEntity.area.name
            ),
            inspectionType = InspectionTypeEntity(
                id = dataEntity.inspectionType.id,
                access = dataEntity.inspectionType.access,
                name = dataEntity.inspectionType.name
            ),
            questions = getQuestions(dataEntity)
        )

    private fun getQuestions(inspection: Inspection): RealmList<QuestionEntity> {
        val survey = SurveyEntity(id = inspection.id)
        val questionsRealm = RealmList<QuestionEntity>()
        inspection.survey.categories.map { category ->
            val categoryEntity = CategoryEntity(
                id = category.id,
                name = category.name,
                survey = survey
            )
            val question = category.questions.map { question ->
                QuestionEntity(
                    id = question.id,
                    name = question.name,
                    selectedAnswerChoiceId = question.selectedAnswerChoiceId,
                    category = categoryEntity,
                    answerChoices = RealmList<AnswerEntity>().apply {
                        addAll(question.answerChoices.map { answer ->
                            AnswerEntity(
                                id = answer.id,
                                name = answer.name,
                                score = answer.score
                            )
                        })
                    }
                )
            }
            questionsRealm.addAll(question)
        }
        return questionsRealm
    }
}