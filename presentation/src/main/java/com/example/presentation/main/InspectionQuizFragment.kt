package com.example.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_ui.presentation.BaseFragment
import com.example.core_ui.presentation.observeEvent
import com.example.presentation.databinding.FragmentInspectionQuizBinding
import com.example.presentation.main.adapter.quiz.QuizAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InspectionQuizFragment : BaseFragment() {

    private var _binding: FragmentInspectionQuizBinding? = null
    private val binding get() = _binding!!

    private val viewModel: InspectionQuizViewModel by viewModels()

    private val adapter = QuizAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInspectionQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvQuizInspections.layoutManager = LinearLayoutManager(requireContext())
        binding.rvQuizInspections.adapter = adapter

        viewModel.items.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.showDialog.observeEvent(viewLifecycleOwner){
            showDialog(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}