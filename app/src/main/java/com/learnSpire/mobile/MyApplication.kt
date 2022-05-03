package com.learnSpire.mobile

import android.app.Application
import android.content.Context

class MyApplication : Application() {

    companion object {
        var contextOfApplication: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        contextOfApplication = applicationContext
    }
}