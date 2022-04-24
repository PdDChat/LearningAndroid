package com.learningandroid.ui.viewmodel

sealed class ResponseStatus<out T> {
    object None : ResponseStatus<Nothing>()
    object Loading : ResponseStatus<Nothing>()
    object ZeroMatch : ResponseStatus<Nothing>()
    data class Success<T>(val value: T?) : ResponseStatus<T>()
    data class Error(val throwable: Throwable) : ResponseStatus<Nothing>()
}
