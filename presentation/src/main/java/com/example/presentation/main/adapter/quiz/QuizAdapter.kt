package com.example.presentation.main.adapter.quiz

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.presentation.main.adapter.utils.SimpleDiffItemCallback
import com.release.domain.model.InspectionQuizItem

class QuizAdapter : ListAdapter<InspectionQuizItem, QuizViewHolder>(SimpleDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        return QuizViewHolder(parent)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}