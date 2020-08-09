package com.example.vitaz.wallchat.Controller

import android.app.Application
import com.example.vitaz.wallchat.Utilities.SharedPrefs

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