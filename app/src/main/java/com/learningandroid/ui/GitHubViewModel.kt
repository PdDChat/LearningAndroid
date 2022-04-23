package com.learningandroid.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learningandroid.model.repository.GitHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(private val repository: GitHubRepository): ViewModel() {

    fun searchUserRepositories(name: String) {
        viewModelScope.launch {
            repository.searchUserRepositories(name)
        }
    }
}