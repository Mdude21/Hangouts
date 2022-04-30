package com.example.hangouts.domain.repository

import com.example.hangouts.domain.models.Contact
import kotlinx.coroutines.flow.Flow

interface ContactRepository {

    suspend fun getContacts() : List<Contact>

    suspend fun getContactByPhoneNumber(phoneNumber: String) : Contact?

    suspend fun insertContact(contact: Contact)

    suspend fun deleteContact(contact: Contact)
}