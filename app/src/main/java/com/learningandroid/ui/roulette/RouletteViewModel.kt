package com.learningandroid.ui.roulette

import androidx.lifecycle.*
import com.learningandroid.model.data.RouletteInfo
import com.learningandroid.model.repository.RouletteRepository
import com.learningandroid.common.ResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RouletteViewModel @Inject constructor(private val repository: RouletteRepository): ViewModel() {

    private val _rouletteInfoStatus = MutableStateFlow<ResponseStatus<List<RouletteInfo>>>(ResponseStatus.None)
    val rouletteInfoStatus: StateFlow<ResponseStatus<List<RouletteInfo>>> = _rouletteInfoStatus

    private var rouletteList: List<RouletteInfo> = listOf()

    fun getRouletteInfo() {
        viewModelScope.launch {
            kotlin.runCatching {
                _rouletteInfoStatus.value = ResponseStatus.Loading
                repository.getRouletteInfo()
            }.onSuccess {
                if (it.isNullOrEmpty()) {
                    _rouletteInfoStatus.value = ResponseStatus.ZeroMatch
                    return@launch
                }

                rouletteList = it
                _rouletteInfoStatus.value = ResponseStatus.Success(it)
            }.onFailure {
                _rouletteInfoStatus.value = ResponseStatus.Error(it)
            }
        }
    }

    fun startRoulette(): String {
        return rouletteList.toMutableList().map { it.name }.shuffled().first()
    }

}