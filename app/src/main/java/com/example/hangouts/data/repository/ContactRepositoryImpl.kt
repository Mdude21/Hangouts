package com.example.hangouts.data.repository

import com.example.hangouts.data.data_source.ContactDao
import com.example.hangouts.domain.models.Contact
import com.example.hangouts.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow

class ContactRepositoryImpl(
    private val dao: ContactDao
) : ContactRepository{
    override fun getContacts(): Flow<List<Contact>> {
        return dao.getContacts()
    }

    override suspend fun getContactById(id: Long): Contact? {
        return dao.getContactById(id)
    }

    override suspend fun insertContact(contact: Contact) {
        dao.insertContact(contact)
    }

    override suspend fun deleteContact(contact: Contact) {
        dao.deleteContact(contact)
    }
}