package com.maku.interviewweatherapp

import android.app.Application
import com.maku.interviewweatherapp.common.logging.Logger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApp: Application() {

    // initiate Bugs nug,firebase analytics, crashlytics, etc ... etc

    override fun onCreate() {
        super.onCreate()

        initLogger()
    }

    private fun initLogger() {
        Logger.init()
    }
}