package com.example.presentation.main.adapter.saved

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.presentation.main.adapter.saved.listener.ItemClickListener
import com.example.presentation.main.adapter.utils.SimpleDiffItemCallback
import com.release.domain.model.InspectionItems

class SavedAdapter(
    private val itemClickListener: ItemClickListener
): ListAdapter<InspectionItems, SavedViewHolder>(SimpleDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedViewHolder {
        return SavedViewHolder(parent, itemClickListener)
    }

    override fun onBindViewHolder(holder: SavedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}