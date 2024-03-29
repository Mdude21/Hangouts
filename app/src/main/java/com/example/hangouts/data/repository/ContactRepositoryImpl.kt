package com.example.hangouts.data.repository

import com.example.hangouts.data.DataBase
import com.example.hangouts.domain.models.Contact
import com.example.hangouts.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow

class ContactRepositoryImpl() : ContactRepository {

    private val dao = DataBase.instance.ContactDao()

    override fun getContacts(): Flow<List<Contact>> {
        return dao.getContacts()
    }

    override suspend fun getContactByPhoneNumber(phoneNumber: String): Contact? {
        return dao.getContactByPhoneNumber(phoneNumber)
    }

    override suspend fun insertContact(contact: Contact) {
        dao.insertContact(contact)
    }

    override suspend fun update(contact: Contact) {
        dao.update(contact)
    }

    override suspend fun deleteContact(contact: Contact) {
        dao.deleteContact(contact)
    }

    override suspend fun getContactById(id: Long): Contact? {
        return dao.getContactById(id)
    }
}