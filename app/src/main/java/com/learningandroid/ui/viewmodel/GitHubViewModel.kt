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

    private val _responseLoginInfoStatus = MutableLiveData<ResponseStatus<LoginInfo>>(ResponseStatus.None)
    val responseLoginInfoStatus: LiveData<ResponseStatus<LoginInfo>> = _responseLoginInfoStatus

    private val _responseRepositoriesStatus = MutableLiveData<ResponseStatus<List<Repositories>>>(ResponseStatus.None)
    val responseRepositoriesStatus: LiveData<ResponseStatus<List<Repositories>>> = _responseRepositoriesStatus

    fun searchLoginInfo(name: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                _responseLoginInfoStatus.value = ResponseStatus.Loading
                repository.searchLoginInfo(name)
            }.onSuccess {
                _responseLoginInfoStatus.value = ResponseStatus.Success(it.body())
            }.onFailure {
                _responseLoginInfoStatus.value = ResponseStatus.Error(it)
            }
        }
    }

    fun searchRepositories(name: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                _responseRepositoriesStatus.value = ResponseStatus.Loading
                repository.searchRepositories(name)
            }.onSuccess {
                _responseRepositoriesStatus.value = ResponseStatus.Success(it.body())
            }.onFailure {
                _responseRepositoriesStatus.value = ResponseStatus.Error(it)
            }
        }
    }
}