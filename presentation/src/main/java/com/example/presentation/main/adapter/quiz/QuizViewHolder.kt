package com.example.presentation.main.adapter.quiz

import android.view.ViewGroup
import com.example.presentation.R
import com.example.presentation.databinding.ItemQuizInspectionBinding
import com.example.presentation.main.adapter.utils.BaseViewHolder
import com.release.domain.model.InspectionQuizItem

class QuizViewHolder(
    parent: ViewGroup
) : BaseViewHolder(parent, R.layout.item_quiz_inspection) {

    private val viewBinding = ItemQuizInspectionBinding.bind(itemView)

    private lateinit var inspectionQuizItems: InspectionQuizItem

    fun bind(inspectionQuizItems: InspectionQuizItem) {
        this.inspectionQuizItems = inspectionQuizItems
        viewBinding.question.text = inspectionQuizItems.question
    }
}