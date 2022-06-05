package com.learningandroid.ui.github

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.learningandroid.databinding.FragmentGithubBinding
import com.learningandroid.ui.github.GitHubTopViewModel.InputState.Input
import com.learningandroid.ui.github.GitHubTopViewModel.InputState.NoInput
import com.learningandroid.ui.github.GitHubTopViewModel.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GitHubTopFragment : Fragment() {

    private var _binding: FragmentGithubBinding? = null
    private val binding get() = _binding

    private val viewModel: GitHubTopViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentGithubBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.inputStatus.collect {
                        when (it) {
                            is UiState.None -> binding?.editRepositoryLayout?.error = null
                            is UiState.Success -> {
                                viewModel.inputText.value = NoInput
                                binding?.editRepositoryLayout?.error = null
                                binding?.editRepositoryName?.text = null
                                findNavController().navigate(GitHubTopFragmentDirections.actionToGitHubInfoFragment(it.value))
                            }
                            is UiState.NoWard -> binding?.editRepositoryLayout?.error = "入力されていません"
                            is UiState.LimitOver -> binding?.editRepositoryLayout?.error = "制限文字数を超えています"
                        }
                }
            }
        }

        binding?.editRepositoryName?.apply {
            setOnClickListener {
                viewModel.inputText.value = Input(text.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}