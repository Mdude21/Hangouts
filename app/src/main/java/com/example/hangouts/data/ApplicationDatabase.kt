package com.example.hangouts.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hangouts.domain.models.Contact

@Database(
    entities = [
        Contact::class,
    ], version = ApplicationDatabase.DB_VERSION
)

abstract class ApplicationDatabase : RoomDatabase(){
    abstract fun ContactDao(): ContactDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "app-database"
    }
}