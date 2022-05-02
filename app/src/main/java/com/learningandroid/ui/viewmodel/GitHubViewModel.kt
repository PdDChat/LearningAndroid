package com.learningandroid.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learningandroid.model.data.LoginInfo
import com.learningandroid.model.data.Repositories
import com.learningandroid.model.repository.GitHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(private val repository: GitHubRepository): ViewModel() {

    private val _loginInfoStatus = MutableLiveData<ResponseStatus<LoginInfo>>(ResponseStatus.None)
    val loginInfoStatus: LiveData<ResponseStatus<LoginInfo>> = _loginInfoStatus

    private val _repositoriesStatus = MutableLiveData<ResponseStatus<List<Repositories>>>(ResponseStatus.None)
    val repositoriesStatus: LiveData<ResponseStatus<List<Repositories>>> = _repositoriesStatus

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