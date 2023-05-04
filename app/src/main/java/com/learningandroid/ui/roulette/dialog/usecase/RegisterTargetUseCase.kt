package com.learningandroid.ui.roulette.dialog.usecase

import com.learningandroid.model.repository.RouletteRepository
import com.learningandroid.ui.roulette.response.RegisterStatus
import javax.inject.Inject

class RegisterTargetUseCase @Inject constructor(
    private val repository: RouletteRepository
){
    suspend fun registerRouletteInfo(name: String): RegisterStatus {
        return repository.registerRouletteInfo(name)
    }
}