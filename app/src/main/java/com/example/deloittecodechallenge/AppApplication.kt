package com.example.deloittecodechallenge

import android.app.Application
import android.content.Context
import com.example.deloittecodechallenge.utils.Languages
import dagger.hilt.android.HiltAndroidApp
import io.paperdb.Paper

@HiltAndroidApp
class AppApplication : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(Languages.setLocale(base))
    }

    override fun onCreate() {
        super.onCreate()
        Paper.init(applicationContext)
    }
}