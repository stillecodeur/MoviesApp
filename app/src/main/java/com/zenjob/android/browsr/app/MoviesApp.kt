package com.zenjob.android.browsr.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MoviesApp:Application() {
    override fun onCreate() {
        super.onCreate()

    }
}