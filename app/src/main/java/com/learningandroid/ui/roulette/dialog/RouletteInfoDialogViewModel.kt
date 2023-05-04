package com.learningandroid.ui.roulette.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learningandroid.ui.roulette.dialog.usecase.DeleteTargetUseCase
import com.learningandroid.ui.roulette.dialog.usecase.RegisterTargetUseCase
import com.learningandroid.ui.roulette.response.DeleteStatus
import com.learningandroid.ui.roulette.response.RegisterStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RouletteInfoDialogViewModel @Inject constructor(
    private val registerUseCase: RegisterTargetUseCase,
    private val deleteUseCase: DeleteTargetUseCase
) : ViewModel() {

    private val _registerStatus: MutableStateFlow<RegisterStatus> = MutableStateFlow(RegisterStatus.None)
    val registerStatus = _registerStatus.asStateFlow()

    private val _deleteStatus: MutableStateFlow<DeleteStatus> = MutableStateFlow(DeleteStatus.None)
    val deleteStatus = _deleteStatus.asStateFlow()

    fun registerRouletteInfo(name: String) {
        viewModelScope.launch {
            _registerStatus.value = registerUseCase.registerRouletteInfo(name)
        }
    }
    fun deleteRouletteInfo(name: String) {
        viewModelScope.launch {
            _deleteStatus.value = deleteUseCase.deleteRouletteInfo(name)
        }
    }

}