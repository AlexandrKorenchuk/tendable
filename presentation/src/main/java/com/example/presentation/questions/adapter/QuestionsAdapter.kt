package com.example.presentation.questions.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.presentation.utils.SimpleDiffItemCallback
import com.release.domain.model.InspectionQuizItem

class QuestionsAdapter : ListAdapter<InspectionQuizItem, QuestionViewHolder>(SimpleDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder(parent)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}