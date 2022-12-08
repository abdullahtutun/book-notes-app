package com.example.booknotes

import android.app.Application
import android.content.Context
import com.example.booknotes.helper.SharedPreferencesHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MobileApplication : Application() {

    companion object {
        lateinit  var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext

        SessionManager.sharedPreferencesHelper =  SharedPreferencesHelper(appContext)
    }

}