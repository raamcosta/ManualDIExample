package com.ramcosta.manualdiexample.di

import androidx.lifecycle.SavedStateHandle
import com.ramcosta.manualdiexample.MyApp
import com.ramcosta.manualdiexample.ui.MyViewModel
import com.ramcosta.manualdiexample.data.MyRepository
import com.ramcosta.manualdiexample.domain.MyUseCase

class DiContainer(
    private val myApp: MyApp
) {

    private val myRepository = MyRepository()

    private val myUseCase get() = MyUseCase(myRepository)

    @Suppress("UNCHECKED_CAST")
    fun <T> createViewModel(
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T = when (modelClass) {

       MyViewModel::class.java -> MyViewModel(myUseCase, handle)

        else -> error("I don't know how to create a $modelClass!")
    } as T
}
