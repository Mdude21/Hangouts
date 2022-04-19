package com.example.hangouts.data.repository

import com.example.hangouts.data.DataBase
import com.example.hangouts.data.data_source.ContactDao
import com.example.hangouts.domain.models.Contact
import com.example.hangouts.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow

class ContactRepositoryImpl() : ContactRepository{

    private val dao = DataBase.instance.ContactDao()

    override suspend fun getContacts(): List<Contact> {
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