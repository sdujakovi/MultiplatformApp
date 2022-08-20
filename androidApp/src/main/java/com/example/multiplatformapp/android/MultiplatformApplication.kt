package com.example.multiplatformapp.android

import android.app.Application
import com.example.multiplatformapp.data.di.initKoin

class MultiplatformApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
        }
    }
}