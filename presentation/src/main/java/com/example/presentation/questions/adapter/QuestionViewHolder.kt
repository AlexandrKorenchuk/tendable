package com.example.presentation.questions.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.example.presentation.R
import com.example.presentation.databinding.ItemQuestionBinding
import com.example.presentation.utils.BaseViewHolder
import com.release.domain.model.QuestionItem

class QuestionViewHolder(
    parent: ViewGroup,
    private val radioButtonClickListener: RadioButtonClickListener
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
            if (questionItem.selectedAnswerId != null && it.id == questionItem.selectedAnswerId)
                radioButton.isChecked = true
            viewBinding.rgAnswers.addView(radioButton)
        }
        viewBinding.rgAnswers.setOnCheckedChangeListener { radioGroup, i ->
            val answerId = questionItem.answers.find {
                it.name == radioGroup.findViewById<RadioButton>(i).text
            }?.id
            if (answerId != null) {
                radioButtonClickListener.onRadioButtonClicked(
                    questionItem.id,
                    answerId
                )
            }
        }
    }
}