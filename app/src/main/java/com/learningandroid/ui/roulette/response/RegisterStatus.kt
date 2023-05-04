package com.learningandroid.ui.roulette.response

sealed class RegisterStatus {
    object NotEntered : RegisterStatus()
    object None : RegisterStatus()
    object Success : RegisterStatus()
    object Error : RegisterStatus()
}
