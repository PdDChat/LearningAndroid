package com.learningandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learningandroid.model.data.LoginInfo
import com.learningandroid.model.repository.GitHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(private val repository: GitHubRepository): ViewModel() {

    private val _responseStatus = MutableStateFlow<ResponseStatus<LoginInfo>>(ResponseStatus.None)
    val responseStatus: StateFlow<ResponseStatus<LoginInfo>> = _responseStatus

    fun searchUserRepositories(name: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                _responseStatus.value = ResponseStatus.Loading
                repository.searchLoginInfo(name)
            }.onSuccess {
                _responseStatus.value = ResponseStatus.Success(it.body())
            }.onFailure {
                _responseStatus.value = ResponseStatus.Error(it)
            }
        }
    }
}