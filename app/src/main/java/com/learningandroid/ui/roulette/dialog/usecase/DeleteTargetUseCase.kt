package com.learningandroid.ui.roulette.dialog.usecase

import com.learningandroid.model.repository.RouletteRepository
import com.learningandroid.ui.roulette.response.DeleteStatus
import javax.inject.Inject

class DeleteTargetUseCase @Inject constructor(
    private val repository: RouletteRepository
){
    suspend fun deleteRouletteInfo(name: String): DeleteStatus {
        return repository.deleteRouletteInfo(name)
    }
}