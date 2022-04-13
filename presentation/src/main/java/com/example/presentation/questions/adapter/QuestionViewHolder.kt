package com.example.presentation.questions.adapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.example.presentation.R
import com.example.presentation.databinding.ItemQuestionBinding
import com.example.presentation.utils.BaseViewHolder
import com.release.domain.model.InspectionItem
import com.release.domain.model.QuestionItem

class QuestionViewHolder(
    parent: ViewGroup
) : BaseViewHolder(parent, R.layout.item_question) {

    private val viewBinding = ItemQuestionBinding.bind(itemView)

    private lateinit var questionItem: QuestionItem

    fun bind(questionItem: QuestionItem) {
        this.questionItem = questionItem
        viewBinding.tvQuestion.text = questionItem.name
        questionItem.answers.forEach {
            val radioButton = RadioButton(itemView.context)
            radioButton.id = View.generateViewId()
            radioButton.text = it.name
            viewBinding.rgAnswers.addView(radioButton)
        }
//        radioButton.setOnClickListener {
//            Log.w("radioButtonId",it.id.toString())
//        }
    }
}