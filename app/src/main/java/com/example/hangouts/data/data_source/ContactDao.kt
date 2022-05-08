package com.example.hangouts.data.data_source

import androidx.room.*
import com.example.hangouts.domain.models.Contact
import com.example.hangouts.domain.models.ContactContract
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contact)

    @Query("SELECT * FROM ${ContactContract.TABLE_NAME} ORDER BY ${ContactContract.Columns.FIRSTNAME} ASC")
    fun getContacts(): Flow<List<Contact>>

    @Query("SELECT * FROM ${ContactContract.TABLE_NAME} WHERE phone_number = :phoneNumber")
    suspend fun getContactByPhoneNumber(phoneNumber: String): Contact?

    @Query("SELECT * FROM ${ContactContract.TABLE_NAME} WHERE id = :id")
    suspend fun getContactById(id: Long): Contact?

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Update
    suspend fun update(contact: Contact)

}