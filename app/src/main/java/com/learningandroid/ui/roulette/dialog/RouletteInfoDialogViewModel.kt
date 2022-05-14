package com.learningandroid.ui.roulette.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learningandroid.model.repository.RouletteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RouletteInfoDialogViewModel @Inject constructor(private val repository: RouletteRepository): ViewModel() {

    fun registerRouletteInfo(name: String) = viewModelScope.launch {
        repository.registerRouletteInfo(name)
    }

    fun deleteRouletteInfo(name: String) = viewModelScope.launch {
        repository.deleteRouletteInfo(name)
    }

}