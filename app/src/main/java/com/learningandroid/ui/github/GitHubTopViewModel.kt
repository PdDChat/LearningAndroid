package com.learningandroid.ui.github

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learningandroid.ui.github.GitHubTopViewModel.InputState.Input
import com.learningandroid.ui.github.GitHubTopViewModel.InputState.NoInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class GitHubTopViewModel @Inject constructor() : ViewModel() {

    sealed class UiState {
        object None : UiState()
        object LimitOver : UiState()
        object NoWard : UiState()
        data class Success(val value: String) : UiState()
    }

    sealed class InputState {
        object NoInput : InputState()
        data class Input(val value: String) : InputState()
    }

    var inputText: MutableStateFlow<InputState> = MutableStateFlow(NoInput)

    val inputStatus: StateFlow<UiState> = inputText.map {
        when (it) {
            is Input -> when {
                it.value.isEmpty() -> UiState.NoWard
                it.value.length > 20 -> UiState.LimitOver
                else -> UiState.Success(it.value)
            }
            is NoInput -> UiState.None
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, UiState.None)
}