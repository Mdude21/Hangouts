package com.example.hangouts.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hangouts.data.repository.ContactRepositoryImpl
import com.example.hangouts.domain.models.Contact
import kotlinx.coroutines.launch

class InfoContactViewModel : ViewModel() {
    private val repository = ContactRepositoryImpl()

    fun deleteContact(contact: Contact) {
        viewModelScope.launch {
            repository.deleteContact(contact)
        }

    }
}