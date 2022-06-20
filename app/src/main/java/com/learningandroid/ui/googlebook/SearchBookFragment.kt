package com.learningandroid.ui.googlebook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.learningandroid.common.ResponseStatus
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

        val adapter = BookAdapter(this)
        binding.viewmodel = viewModel
        binding.bookRecyclerView.adapter = adapter

        lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.bookInfoStatus.collect {
                    when (it) {
                        is ResponseStatus.Success -> {
                            adapter.submitList(it.value)
                        }
                        else -> {}
                    }
                }
            }
        }

        binding.searchButton.setOnClickListener {
            viewModel.getBookInfo(binding.inputBookName.text.toString())
        }

    }
}