package com.example.presentation.questions.adapter

import android.view.ViewGroup
import com.example.presentation.R
import com.example.presentation.databinding.ItemQuestionBinding
import com.example.presentation.utils.BaseViewHolder
import com.release.domain.model.InspectionItem

class QuestionViewHolder(
    parent: ViewGroup
) : BaseViewHolder(parent, R.layout.item_question) {

    private val viewBinding = ItemQuestionBinding.bind(itemView)

    private lateinit var inspectionItems: InspectionItem

    fun bind(inspectionItems: InspectionItem) {
        this.inspectionItems = inspectionItems
    }
}