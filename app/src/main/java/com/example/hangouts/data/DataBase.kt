package com.example.hangouts.data

import android.content.Context
import androidx.room.Room

object DataBase {
    lateinit var instance: ContactDatabase
        private set

    fun init(context: Context) {
        instance = Room.databaseBuilder(
            context,
            ContactDatabase::class.java,
            ContactDatabase.DB_NAME
        )
            .build()
    }
}