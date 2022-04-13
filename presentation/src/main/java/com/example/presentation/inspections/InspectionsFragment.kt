package com.example.presentation.inspections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_ui.presentation.BaseFragment
import com.example.core_ui.presentation.observeEvent
import com.example.presentation.databinding.FragmentInspectionsBinding
import com.example.presentation.inspections.adapter.SavedAdapter
import com.example.presentation.inspections.adapter.ItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InspectionsFragment : BaseFragment(), ItemClickListener {

    private var _binding: FragmentInspectionsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: InspectionsViewModel by viewModels()

    private val adapter = SavedAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInspectionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogout.setOnClickListener {
            viewModel.onLogoutButtonClicked()
        }

        binding.btnStart.setOnClickListener {
            viewModel.onStartButtonClicked()
        }

        binding.btnSubmit.setOnClickListener {
            viewModel.onSubmitButtonClicked()
        }

        viewModel.items.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.rvSavedInspections.visibility = View.VISIBLE
                binding.btnSubmit.visibility = View.VISIBLE
                binding.rvSavedInspections.layoutManager = LinearLayoutManager(requireContext())
                binding.rvSavedInspections.adapter = adapter
                adapter.submitList(it)
            }
        }

        viewModel.visibility.observe(viewLifecycleOwner) {
            binding.tvNoStoredInspections.visibility = it
            binding.btnStart.visibility = it
        }

        viewModel.showDialog.observeEvent(viewLifecycleOwner) {
            showDialog(it)
        }

        viewModel.snackBarCommand.observeEvent(viewLifecycleOwner) {
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