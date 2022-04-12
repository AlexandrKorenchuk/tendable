package com.example.presentation.questions.adapter

import android.view.ViewGroup
import com.example.presentation.R
import com.example.presentation.databinding.ItemQuestionBinding
import com.example.presentation.utils.BaseViewHolder
import com.release.domain.model.InspectionQuizItem

class QuestionViewHolder(
    parent: ViewGroup
) : BaseViewHolder(parent, R.layout.item_question) {

    private val viewBinding = ItemQuestionBinding.bind(itemView)

    private lateinit var inspectionQuizItems: InspectionQuizItem

    fun bind(inspectionQuizItems: InspectionQuizItem) {
        this.inspectionQuizItems = inspectionQuizItems
    }
}