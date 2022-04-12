package com.example.presentation.inspections.adapter

import android.view.ViewGroup
import com.example.presentation.R
import com.example.presentation.databinding.ItemInspectionBinding
import com.example.presentation.utils.BaseViewHolder
import com.release.domain.model.InspectionQuizItem

class SavedViewHolder(
    parent: ViewGroup,
    private val itemClickListener: ItemClickListener
) : BaseViewHolder(parent, R.layout.item_inspection) {

    private val viewBinding = ItemInspectionBinding.bind(itemView)

    private lateinit var inspectionItems: InspectionQuizItem

    fun bind(inspectionItems: InspectionQuizItem) {
        this.inspectionItems = inspectionItems
        viewBinding.tvAreaValue.text = inspectionItems.area
        viewBinding.tvTypeValue.text = inspectionItems.type
        viewBinding.tvAccessValue.text = inspectionItems.access

        viewBinding.tvContinue.setOnClickListener {
            itemClickListener.onContinueSavedInspectionClicked(inspectionItems.id)
        }
    }
}