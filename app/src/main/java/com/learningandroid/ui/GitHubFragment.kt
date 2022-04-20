package com.learningandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.learningandroid.databinding.FragmentGitHubBinding

class GitHubFragment : Fragment() {

    private var _binding: FragmentGitHubBinding? = null
    private val binding get() = _binding

    private val viewModel: GithubViewModel by viewModels {
        GithubViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentGitHubBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // viewModel.searchUserRepositories("PddChat")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}