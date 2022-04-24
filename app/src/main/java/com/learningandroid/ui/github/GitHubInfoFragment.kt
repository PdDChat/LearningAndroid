package com.learningandroid.ui.github

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.learningandroid.databinding.FragmentGithubInfoBinding
import com.learningandroid.model.data.LoginInfo
import com.learningandroid.ui.viewmodel.GithubViewModel
import com.learningandroid.ui.viewmodel.ResponseStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GitHubInfoFragment : Fragment() {

    private var _binding: FragmentGithubInfoBinding? = null
    private val binding get() = _binding

    private val args: GitHubInfoFragmentArgs by navArgs()

    private val viewModel: GithubViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentGithubInfoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.searchUserRepositories(args.searchName)

        lifecycleScope.launch {
            viewModel.responseStatus.collect {
                when (it) {
                    is ResponseStatus.Success -> {
                        setupView(it.value)
                    }
                    else -> {}
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupView(info: LoginInfo?) {
        binding?.loginInfoLayout?.apply {
            loginName.text = info?.loginName

            Glide.with(requireActivity())
                .load(info?.avatarUrl)
                .into(loginIcon)
        }
    }

}