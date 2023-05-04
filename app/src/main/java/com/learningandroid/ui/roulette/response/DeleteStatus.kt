package com.learningandroid.ui.roulette.response

sealed class DeleteStatus {
    object NotEntered : DeleteStatus()
    object None : DeleteStatus()
    object Success : DeleteStatus()
    object Error : DeleteStatus()
}
