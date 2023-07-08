package com.ramcosta.manualdiexample

import android.app.Application
import com.ramcosta.manualdiexample.di.DiContainer

class MyApp : Application() {

    val diContainer by lazy {
        DiContainer(this)
    }
}