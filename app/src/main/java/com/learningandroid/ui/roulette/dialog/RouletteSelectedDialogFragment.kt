package com.learningandroid.ui.roulette.dialog

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.learningandroid.databinding.DialogRouletteSelectedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RouletteSelectedDialogFragment : DialogFragment() {

    private val args: RouletteSelectedDialogFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        super.onCreateDialog(savedInstanceState)

        val binding = DialogRouletteSelectedBinding.inflate(requireActivity().layoutInflater).apply {
            rouletteSelectedName.text = args.selectedName
            positiveButton.setOnClickListener {
                dismiss()
            }
        }

        return AlertDialog.Builder(requireContext()).setView(binding.root).create()
    }
}