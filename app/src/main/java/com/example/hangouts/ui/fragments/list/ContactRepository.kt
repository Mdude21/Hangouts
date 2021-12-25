package com.example.hangouts.ui.fragments.list

import com.example.hangouts.data.DataBase
import com.example.hangouts.domain.models.Contact

class ContactRepository {
    private val contactDao = DataBase.instance.ContactDao()

    fun saveUser(contact: Contact) {
        contactDao.insertContact(listOf(contact))
    }

    fun getAllContacts(): Contact {
        return contactDao.getAllUsers()
    }
}