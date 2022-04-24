package com.learningandroid.ui.github

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.learningandroid.databinding.FragmentGithubBinding
import com.learningandroid.ui.viewmodel.GithubViewModel
import com.learningandroid.ui.viewmodel.ResponseStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GitHubFragment : Fragment() {

    private var _binding: FragmentGithubBinding? = null
    private val binding get() = _binding

    private val viewModel: GithubViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentGithubBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.responseStatus.collect {
                when (it) {
                    is ResponseStatus.Success -> {
                        binding?.name?.text = it.value?.login
                    }
                    else -> {}
                }
            }
        }

        binding?.searchButton?.setOnClickListener {
            viewModel.searchUserRepositories(binding?.editRepositoryName?.text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}