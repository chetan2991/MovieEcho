package com.chetan.movieecho

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieEchoApplication  : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}