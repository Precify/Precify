package com.rohit2810.coview

import android.app.Application
import timber.log.Timber

class ApplicationClass : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

    companion object{
        var position : Int = 0;
    }
}