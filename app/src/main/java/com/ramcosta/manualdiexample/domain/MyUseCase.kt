package com.ramcosta.manualdiexample.domain

import com.ramcosta.manualdiexample.data.MyRepository

class MyUseCase(
    private val myRepository: MyRepository
) {

    fun getSomething(): String {
        return myRepository.getSomething()
    }
}
