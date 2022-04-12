package com.release.data.database.entity

open class QuestionEntity(
    var id: Int,
    var name: String,
    var selectedAnswerChoiceId: Int,
    var answerChoices: List<AnswerEntity>,
    var category: CategoryEntity
)