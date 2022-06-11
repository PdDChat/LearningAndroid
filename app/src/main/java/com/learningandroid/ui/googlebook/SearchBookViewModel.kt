package com.learningandroid.ui.googlebook

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchBookViewModel @Inject constructor(): ViewModel() {

    fun setTitle() = "SearchBookFragment"
}