package com.learningandroid.ui.roulette

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.learningandroid.databinding.DialogRegisterBinding

class RegisterDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        super.onCreateDialog(savedInstanceState)

        val binding = DialogRegisterBinding.inflate(requireActivity().layoutInflater)
        binding.registerButton.setOnClickListener {
            // TODO sharedPreferenceに登録
        }
        binding.cancelButton.setOnClickListener { dismiss() }

        return AlertDialog.Builder(requireContext()).setView(binding.root).create()
    }
}