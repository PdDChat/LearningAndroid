package com.learningandroid.ui.roulette

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.learningandroid.databinding.FragmentRouletteBinding
import com.learningandroid.ui.viewmodel.ResponseStatus
import com.learningandroid.ui.viewmodel.RouletteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RouletteFragment : Fragment() {

    private var _binding: FragmentRouletteBinding? = null
    private val binding get() = _binding

    private val viewModel: RouletteViewModel by viewModels()

    private lateinit var adapter: RouletteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentRouletteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView()

        setUpObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpView() {
        adapter = RouletteAdapter()
        binding?.rouletteRecyclerview?.adapter = adapter

        binding?.addButton?.setOnClickListener {
            findNavController().navigate(RouletteFragmentDirections.actionNavRouletteToRegisterDialogFragment())
        }
    }

    private fun setUpObserver() {
        viewModel.getRouletteInfo()

        lifecycleScope.launch {
            viewModel.rouletteInfoStatus.observe(viewLifecycleOwner) {
                when (it) {
                    is ResponseStatus.Success -> {
                        adapter.submitList(it.value?.rouletteInfo)
                    }
                    else -> {}
                }
            }
        }
    }
}