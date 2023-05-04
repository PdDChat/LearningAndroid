package com.learningandroid.ui.roulette.dialog

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.learningandroid.R
import com.learningandroid.databinding.DialogRouletteInfoSettingBinding
import com.learningandroid.ui.roulette.RouletteFragment.Companion.RESULT_DELETE
import com.learningandroid.ui.roulette.RouletteFragment.Companion.RESULT_REGISTER
import com.learningandroid.ui.roulette.response.DeleteStatus
import com.learningandroid.ui.roulette.response.RegisterStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RouletteInfoSettingDialogFragment : DialogFragment() {

    private val args: RouletteInfoSettingDialogFragmentArgs by navArgs()

    private val viewModel: RouletteInfoDialogViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        super.onCreateDialog(savedInstanceState)

        val binding = DialogRouletteInfoSettingBinding.inflate(requireActivity().layoutInflater)
        setUpView(binding)

        return AlertDialog.Builder(requireContext()).setView(binding.root).create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lifecycleScope.launch {
            viewModel.registerStatus.collect {
                when (it) {
                    RegisterStatus.Success -> {
                        findNavController().previousBackStackEntry?.savedStateHandle?.set(RESULT_REGISTER, true)
                        dismiss()
                    }
                    RegisterStatus.Error -> {
                        findNavController().previousBackStackEntry?.savedStateHandle?.set(RESULT_REGISTER, false)
                        dismiss()
                    }
                    RegisterStatus.None -> {
                        // nop
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewModel.deleteStatus.collect {
                when (it) {
                    DeleteStatus.Success -> {
                        findNavController().previousBackStackEntry?.savedStateHandle?.set(RESULT_DELETE, true)
                        dismiss()
                    }
                    DeleteStatus.Error -> {
                        findNavController().previousBackStackEntry?.savedStateHandle?.set(RESULT_DELETE, false)
                        dismiss()
                    }
                    DeleteStatus.None -> {
                        // nop
                    }
                }
            }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun setUpView(binding: DialogRouletteInfoSettingBinding) {
        binding.title.text =
            if (args.isRegister) getString(R.string.register) else getString(R.string.delete)
        binding.positiveButton.setOnClickListener {
            val name = binding.registerEditText.text.toString()

            if (args.isRegister) viewModel.registerRouletteInfo(name)
            else viewModel.deleteRouletteInfo(name)
        }
        binding.cancelButton.setOnClickListener { dismiss() }
    }
}