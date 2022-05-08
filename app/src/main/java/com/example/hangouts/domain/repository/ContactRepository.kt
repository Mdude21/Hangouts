package com.example.hangouts.domain.repository

import com.example.hangouts.domain.models.Contact
import kotlinx.coroutines.flow.Flow

interface ContactRepository {

    fun getContacts(): Flow<List<Contact>>

    suspend fun getContactByPhoneNumber(phoneNumber: String): Contact?

    suspend fun insertContact(contact: Contact)

    suspend fun deleteContact(contact: Contact)

    suspend fun getContactById(id: Long): Contact?

    suspend fun update(contact: Contact)
}