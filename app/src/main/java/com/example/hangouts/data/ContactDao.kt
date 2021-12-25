package com.example.hangouts.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hangouts.domain.models.Contact
import com.example.hangouts.domain.models.ContactContract

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contact: List<Contact>)

    @Query("SELECT * FROM ${ContactContract.TABLE_NAME}")
    fun getAllUsers(): Contact
}