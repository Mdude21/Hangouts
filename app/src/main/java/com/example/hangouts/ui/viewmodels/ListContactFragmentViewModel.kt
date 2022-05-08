package com.example.hangouts.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hangouts.data.repository.ContactRepositoryImpl
import com.example.hangouts.domain.models.Contact
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListContactFragmentViewModel : ViewModel() {

    private val repository = ContactRepositoryImpl()

    private val contactListLiveData = MutableLiveData<List<Contact>>()

    val contactList: LiveData<List<Contact>>
        get() = contactListLiveData

    fun getContactsFromDb() {
        viewModelScope.launch {
            repository.getContacts().collect {
                contactListLiveData.postValue(it)
            }
        }
    }
}