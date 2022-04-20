package com.learningandroid.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.learningandroid.model.repository.GithubRepository
import kotlinx.coroutines.launch

class GithubViewModel(private val repository: GithubRepository): ViewModel() {

    fun searchUserRepositories(name: String) {
        viewModelScope.launch {
            repository.searchUserRepositories(name)
        }
    }
}

class GithubViewModelFactory(
    private val repository: GithubRepository = GithubRepository()
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GithubViewModel(repository) as T
    }
}