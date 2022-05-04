package com.learningandroid.ui.roulette

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.learningandroid.databinding.DialogRegisterBinding
import com.learningandroid.ui.viewmodel.RegisterDialogViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterDialogFragment : DialogFragment() {

    private val viewModel: RegisterDialogViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        super.onCreateDialog(savedInstanceState)

        val binding = DialogRegisterBinding.inflate(requireActivity().layoutInflater)
        binding.registerButton.setOnClickListener {
            val name = binding.registerEditText.text.toString()
            viewModel.registerRouletteInfo(name)
            dismiss()
        }
        binding.cancelButton.setOnClickListener { dismiss() }

        return AlertDialog.Builder(requireContext()).setView(binding.root).create()
    }
}