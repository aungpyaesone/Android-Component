package com.aungpyaesone.testeverything

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(applicationContext)
    }
}