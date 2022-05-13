package com.learningandroid.ui.github

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.learningandroid.databinding.FragmentGithubBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GitHubTopFragment : Fragment() {

    private var _binding: FragmentGithubBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentGithubBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.editRepositoryName?.apply {
            setOnClickListener {
                findNavController().navigate(GitHubTopFragmentDirections.actionToGitHubInfoFragment(text.toString()))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}