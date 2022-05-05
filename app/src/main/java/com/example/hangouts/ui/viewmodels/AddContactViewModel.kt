package com.example.hangouts.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hangouts.data.repository.ContactRepositoryImpl
import com.example.hangouts.domain.models.Contact
import kotlinx.coroutines.launch

class AddContactViewModel : ViewModel() {
    private val repository = ContactRepositoryImpl()

    private val pathImageMutableLiveData = MutableLiveData<String>()

    val pathImageLiveData : LiveData<String>
    get() = pathImageMutableLiveData

    fun addContact(contact : Contact) {
        viewModelScope.launch {
            repository.insertContact(contact)
        }
    }

    fun updateContact(contact: Contact) {
        viewModelScope.launch {
            repository.update(contact)
        }
    }

    fun savePathImage(pathImage : String){
        pathImageMutableLiveData.postValue(pathImage)
    }



}