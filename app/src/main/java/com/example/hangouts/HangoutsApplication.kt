package com.example.hangouts

import android.app.Application
import com.example.hangouts.data.DataBase

class HangoutsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DataBase.init(this)
    }
}