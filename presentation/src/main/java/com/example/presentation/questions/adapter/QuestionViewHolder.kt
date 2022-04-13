package com.example.presentation.questions.adapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.presentation.R
import com.example.presentation.databinding.ItemQuestionBinding
import com.example.presentation.inspections.adapter.ItemClickListener
import com.example.presentation.utils.BaseViewHolder
import com.release.domain.model.InspectionItem
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
            viewBinding.rgAnswers.addView(radioButton)
        }
        Log.w("selectedAnswerInDB", questionItem.selectedAnswerId.toString())


        viewBinding.rgAnswers.setOnCheckedChangeListener { radioGroup, i ->
           val answerId = questionItem.answers.find {
                it.name == radioGroup.findViewById<RadioButton>(i).text
            }?.id
            if(answerId!=null){
                radioButtonClickListener.onRadioButtonClicked(
                    questionItem.id,
                    answerId
                )
            }
        }
    }
}