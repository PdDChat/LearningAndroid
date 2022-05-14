package com.learningandroid.ui.roulette.dialog

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.learningandroid.R
import com.learningandroid.databinding.DialogRouletteInfoSettingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RouletteInfoSettingDialogFragment : DialogFragment() {

    interface OnRouletteInfoSettingClickListener {
        fun onPositiveClick()
    }

    private val args: RouletteInfoSettingDialogFragmentArgs by navArgs()

    private val viewModel: RouletteInfoDialogViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        super.onCreateDialog(savedInstanceState)

        val binding = DialogRouletteInfoSettingBinding.inflate(requireActivity().layoutInflater)
        binding.title.text = if (args.isRegister) getString(R.string.register) else getString(R.string.delete)
        binding.positiveButton.setOnClickListener {
            val name = binding.registerEditText.text.toString()

            if (args.isRegister) viewModel.registerRouletteInfo(name)
            else viewModel.deleteRouletteInfo(name)

            val listener = parentFragmentManager.fragments.first() as OnRouletteInfoSettingClickListener
            listener.onPositiveClick()

            dismiss()
        }
        binding.cancelButton.setOnClickListener { dismiss() }

        return AlertDialog.Builder(requireContext()).setView(binding.root).create()
    }
}