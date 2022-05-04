package com.learningandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learningandroid.model.repository.RouletteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterDialogViewModel @Inject constructor(private val repository: RouletteRepository): ViewModel() {

    fun registerRouletteInfo(name: String) {
        viewModelScope.launch {
            repository.registerRouletteInfo(name)
        }
    }

}