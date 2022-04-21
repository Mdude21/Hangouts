package com.example.hangouts.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hangouts.data.repository.ContactRepositoryImpl
import com.example.hangouts.domain.models.Contact
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    val repository = ContactRepositoryImpl()

    fun addContactList(contact : Contact) {
        viewModelScope.launch {
            repository.insertContact(contact)
        }
    }
}