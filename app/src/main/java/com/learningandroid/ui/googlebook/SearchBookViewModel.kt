package com.learningandroid.ui.googlebook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learningandroid.common.ResponseStatus
import com.learningandroid.model.data.BookInfo
import com.learningandroid.model.data.Items
import com.learningandroid.model.repository.BookRepository
import com.learningandroid.model.repository.BookRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchBookViewModel @Inject constructor(private val repository: BookRepository): ViewModel() {

    private val _bookInfoStatus = MutableStateFlow<ResponseStatus<List<Items>>>(ResponseStatus.None)
    val bookInfoStatus: StateFlow<ResponseStatus<List<Items>>> = _bookInfoStatus

    fun getBookInfo(query: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                _bookInfoStatus.value = ResponseStatus.Loading
                repository.searchBookInfo(query)
            }.onSuccess {
                _bookInfoStatus.value = ResponseStatus.Success(it.body()?.items)
            }.onFailure {
                _bookInfoStatus.value = ResponseStatus.Error(it)
            }
        }
    }
}