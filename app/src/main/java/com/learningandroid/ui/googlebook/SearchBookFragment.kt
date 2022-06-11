package com.learningandroid.ui.googlebook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.learningandroid.databinding.FragmentSearchBookBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchBookFragment : Fragment() {

    private lateinit var binding: FragmentSearchBookBinding

    private val viewModel: SearchBookViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSearchBookBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewmodel = viewModel
    }
}