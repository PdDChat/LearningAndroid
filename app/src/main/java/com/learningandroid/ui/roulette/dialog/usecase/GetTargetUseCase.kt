package com.learningandroid.ui.roulette.dialog.usecase

import com.learningandroid.model.data.RouletteInfo
import com.learningandroid.model.repository.RouletteRepository
import javax.inject.Inject

class GetTargetUseCase @Inject constructor(
    private val repository: RouletteRepository
){
    suspend fun getRouletteInfo(): List<RouletteInfo> {
        return repository.getRouletteInfo()
    }
}