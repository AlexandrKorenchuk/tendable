package com.example.presentation.main.adapter.saved

import android.view.ViewGroup
import com.example.presentation.R
import com.example.presentation.databinding.ItemSavedInspectionBinding
import com.example.presentation.main.adapter.saved.listener.ItemClickListener
import com.example.presentation.main.adapter.utils.BaseViewHolder
import com.release.domain.model.InspectionQuizItem

class SavedViewHolder(
    parent: ViewGroup,
    private val itemClickListener: ItemClickListener
) : BaseViewHolder(parent, R.layout.item_saved_inspection) {

    private val viewBinding = ItemSavedInspectionBinding.bind(itemView)

    private lateinit var inspectionItems: InspectionQuizItem

    fun bind(inspectionItems: InspectionQuizItem) {
        this.inspectionItems = inspectionItems
        viewBinding.area.text = inspectionItems.area
        viewBinding.type.text = inspectionItems.type
        viewBinding.accesss.text = inspectionItems.access
        viewBinding.questions.text = inspectionItems.questions

        viewBinding.tvContinue.setOnClickListener {
            itemClickListener.onContinueSavedInspectionClicked(inspectionItems.id)
        }
    }

}