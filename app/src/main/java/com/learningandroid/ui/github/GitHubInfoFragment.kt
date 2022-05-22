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
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.learningandroid.common.ResponseStatus
import com.learningandroid.databinding.FragmentGithubInfoBinding
import com.learningandroid.model.data.LoginInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GitHubInfoFragment : Fragment() {

    private var _binding: FragmentGithubInfoBinding? = null
    private val binding get() = _binding

    private val args: GitHubInfoFragmentArgs by navArgs()

    private val viewModel: GithubViewModel by viewModels()

    private lateinit var adapter: GitHubAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentGithubInfoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateView()

        setupObserver()
    }

    private fun updateView() {
        adapter = GitHubAdapter()
        binding?.repositoriesRecyclerview?.adapter = adapter
    }

    private fun loginInfoView(info: LoginInfo?) {
        binding?.loginInfoLayout?.apply {
            loginName.text = info?.loginName

            Glide.with(requireActivity())
                .load(info?.avatarUrl)
                .into(loginIcon)
        }
    }

    private fun setupObserver() {
        viewModel.searchLoginInfo(args.searchName)
        viewModel.searchRepositories(args.searchName)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loginInfoStatus.collect {
                    when (it) {
                        is ResponseStatus.Success -> {
                            loginInfoView(it.value)
                        }
                        else -> {}
                    }
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.repositoriesStatus.collect {
                    when (it) {
                        is ResponseStatus.Success -> {
                            adapter.submitList(it.value)
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}