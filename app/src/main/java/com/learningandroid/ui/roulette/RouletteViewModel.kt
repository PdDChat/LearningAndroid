package com.learningandroid.ui.roulette

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.learningandroid.common.ResponseStatus
import com.learningandroid.model.data.RouletteInfo
import com.learningandroid.ui.roulette.dialog.usecase.GetTargetUseCase
import com.learningandroid.ui.roulette.worker.RouletteWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class RouletteViewModel @Inject constructor(
    private val worker: WorkManager,
    private val useCase: GetTargetUseCase
): ViewModel() {

    private val _rouletteInfoStatus = MutableStateFlow<ResponseStatus<List<RouletteInfo>>>(ResponseStatus.None)
    val rouletteInfoStatus: StateFlow<ResponseStatus<List<RouletteInfo>>> = _rouletteInfoStatus

    private var rouletteList: List<RouletteInfo> = listOf()

    init {
        getRouletteInfo()

        setWorkManager()
    }

    fun getRouletteInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _rouletteInfoStatus.value = ResponseStatus.Loading
                useCase.getRouletteInfo()
            }.onSuccess {
                if (it.isEmpty()) {
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


    private fun setWorkManager(){
        val request = PeriodicWorkRequestBuilder<RouletteWorker>(15, TimeUnit.MINUTES)
            .build()
        worker.enqueueUniquePeriodicWork(
            RouletteWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
    }

}