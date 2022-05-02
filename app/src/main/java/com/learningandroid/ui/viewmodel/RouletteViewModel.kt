package com.learningandroid.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learningandroid.model.data.RouletteListInfo
import com.learningandroid.model.repository.RouletteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RouletteViewModel @Inject constructor(private val repository: RouletteRepository): ViewModel() {

    private val _rouletteInfoStatus = MutableLiveData<ResponseStatus<RouletteListInfo>>(ResponseStatus.None)
    val rouletteInfoStatus: LiveData<ResponseStatus<RouletteListInfo>> = _rouletteInfoStatus

    fun getRouletteInfo() {
        viewModelScope.launch {
            kotlin.runCatching {
                _rouletteInfoStatus.value = ResponseStatus.Loading
                repository.getRouletteInfo()
            }.onSuccess {
                _rouletteInfoStatus.value = ResponseStatus.Success(it.body())
            }.onFailure {
                _rouletteInfoStatus.value = ResponseStatus.Error(it)
            }
        }
    }

}