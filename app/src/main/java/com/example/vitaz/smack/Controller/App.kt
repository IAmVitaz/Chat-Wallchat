package com.example.vitaz.smack.Controller

import android.app.Application
import com.example.vitaz.smack.Utilities.SharedPrefs

class App: Application() {
    //app  android:name=".Controller.App">   to AndroidManifest file

    companion object {
        lateinit var prefs: SharedPrefs
    }

    override fun onCreate() {
        prefs = SharedPrefs(applicationContext)

        super.onCreate()
    }

}