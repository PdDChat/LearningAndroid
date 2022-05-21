package com.learningandroid.ui.github

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learningandroid.common.ResponseStatus
import com.learningandroid.model.data.LoginInfo
import com.learningandroid.model.data.Repositories
import com.learningandroid.model.repository.GitHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(private val repository: GitHubRepository): ViewModel() {

    private val _loginInfoStatus = MutableStateFlow<ResponseStatus<LoginInfo>>(ResponseStatus.None)
    val loginInfoStatus: StateFlow<ResponseStatus<LoginInfo>> = _loginInfoStatus

    private val _repositoriesStatus = MutableStateFlow<ResponseStatus<List<Repositories>>>(ResponseStatus.None)
    val repositoriesStatus: StateFlow<ResponseStatus<List<Repositories>>> = _repositoriesStatus

    fun searchLoginInfo(name: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                _loginInfoStatus.value = ResponseStatus.Loading
                repository.searchLoginInfo(name)
            }.onSuccess {
                _loginInfoStatus.value = ResponseStatus.Success(it.body())
            }.onFailure {
                _loginInfoStatus.value = ResponseStatus.Error(it)
            }
        }
    }

    fun searchRepositories(name: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                _repositoriesStatus.value = ResponseStatus.Loading
                repository.searchRepositories(name)
            }.onSuccess {
                _repositoriesStatus.value = ResponseStatus.Success(it.body())
            }.onFailure {
                _repositoriesStatus.value = ResponseStatus.Error(it)
            }
        }
    }
}