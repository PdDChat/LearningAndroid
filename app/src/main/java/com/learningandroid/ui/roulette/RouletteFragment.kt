package com.learningandroid.ui.roulette

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.learningandroid.R
import com.learningandroid.common.ResponseStatus
import com.learningandroid.databinding.FragmentRouletteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RouletteFragment : Fragment() {

    companion object {
        const val RESULT_REGISTER = "result_register"
        const val RESULT_DELETE = "result_delete"
    }

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

        updateView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpView() {
        adapter = RouletteAdapter()
        binding?.rouletteRecyclerview?.adapter = adapter

        binding?.addButton?.setOnClickListener {
            findNavController().navigate(RouletteFragmentDirections.actionToSettingDialogFragment(true))
        }

        binding?.deleteButton?.setOnClickListener {
            findNavController().navigate(RouletteFragmentDirections.actionToSettingDialogFragment(false))
        }

        binding?.zeroMatchButton?.setOnClickListener {
            findNavController().navigate(RouletteFragmentDirections.actionToSettingDialogFragment(true))
        }

        binding?.startRouletteButton?.setOnClickListener {
            val result = viewModel.startRoulette()
            findNavController().navigate(RouletteFragmentDirections.actionToRouletteSelectedDialogFragment(result))
        }
    }

    private fun updateView() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.rouletteInfoStatus.collect {
                    when (it) {
                        is ResponseStatus.Success -> {
                            binding?.apply {
                                successLayout.visibility = View.VISIBLE
                                zeroMatchLayout.visibility = View.GONE
                            }
                            adapter.submitList(it.value)
                        }
                        is ResponseStatus.ZeroMatch -> {
                            binding?.apply {
                                successLayout.visibility = View.GONE
                                zeroMatchLayout.visibility = View.VISIBLE
                            }
                        }
                        else -> {}
                    }
                }
            }
        }

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Boolean>(
            RESULT_REGISTER
        )?.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.getRouletteInfo()
            } else {
                Toast.makeText(
                    context,
                    getString(R.string.already_register_error_text),
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Boolean>(
            RESULT_DELETE
        )?.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.getRouletteInfo()
            } else {
                Toast.makeText(
                    context,
                    getString(R.string.already_delete_error_text),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}