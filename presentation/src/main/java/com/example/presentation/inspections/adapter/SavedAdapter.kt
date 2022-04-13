package com.example.presentation.inspections.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.presentation.utils.SimpleDiffItemCallback
import com.release.domain.model.InspectionItem

class SavedAdapter(
    private val itemClickListener: ItemClickListener
): ListAdapter<InspectionItem, SavedViewHolder>(SimpleDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedViewHolder {
        return SavedViewHolder(parent, itemClickListener)
    }

    override fun onBindViewHolder(holder: SavedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}