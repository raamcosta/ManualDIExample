package com.ramcosta.manualdiexample.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ramcosta.manualdiexample.domain.MyUseCase

class MyViewModel(
    private val myUseCase: MyUseCase,
    private val handle: SavedStateHandle
) : ViewModel() {

    val uiState = myUseCase.getSomething()
}