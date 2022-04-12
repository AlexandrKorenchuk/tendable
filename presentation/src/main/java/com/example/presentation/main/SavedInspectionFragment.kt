package com.example.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_ui.presentation.BaseFragment
import com.example.core_ui.presentation.observeEvent
import com.example.presentation.databinding.FragmentSavedInspectionBinding
import com.example.presentation.main.adapter.saved.SavedAdapter
import com.example.presentation.main.adapter.saved.listener.ItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedInspectionFragment : BaseFragment(), ItemClickListener {

    private var _binding: FragmentSavedInspectionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SavedInspectionViewModel by viewModels()

    private val adapter = SavedAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedInspectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogout.setOnClickListener {
            viewModel.onLogoutButtonClicked()
        }

        binding.btnStart.setOnClickListener {
            //start new
            viewModel.onStartButtonClicked()
        }

        binding.btnSubmit.setOnClickListener {
            //send
            viewModel.onSubmitButtonClicked()
        }

        viewModel.items.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.rvSavedInspections.visibility = View.VISIBLE
                binding.btnSubmit.visibility = View.VISIBLE
                binding.rvSavedInspections.layoutManager = LinearLayoutManager(requireContext())
                binding.rvSavedInspections.adapter = adapter
                adapter.submitList(it)
            } else {
                binding.tvNoStoredInspections.visibility = View.VISIBLE
                binding.btnStart.visibility = View.VISIBLE
            }
        }

        viewModel.showDialog.observeEvent(viewLifecycleOwner){
            showDialog(it)
        }

        viewModel.snackBarCommand.observeEvent(viewLifecycleOwner){
            showSnackBar(it)
        }

        viewModel.navigationEvent.observeEvent(viewLifecycleOwner) {
            navigate(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onContinueSavedInspectionClicked(id: Int) {
        viewModel.onContinueButtonClicked(id)
    }
}