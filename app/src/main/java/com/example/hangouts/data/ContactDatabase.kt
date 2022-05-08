package com.example.hangouts.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hangouts.data.data_source.ContactDao
import com.example.hangouts.data.data_source.MessageDao
import com.example.hangouts.domain.models.Contact
import com.example.hangouts.domain.models.Message

@Database(
    entities = [
        Contact::class,
        Message::class
    ], version = ContactDatabase.DB_VERSION
)

abstract class ContactDatabase : RoomDatabase() {
    abstract fun ContactDao(): ContactDao
    abstract fun MessageDao(): MessageDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "app-database"
    }
}