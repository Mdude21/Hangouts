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

    val pathImageLiveData: LiveData<String>
        get() = pathImageMutableLiveData

    private val isValidPhoneNumberMutableLiveData = MutableLiveData<Boolean>()

    val isValidPhoneNumberLiveData: LiveData<Boolean>
        get() = isValidPhoneNumberMutableLiveData

    fun addContact(contact: Contact) {
        viewModelScope.launch {
            repository.insertContact(contact)
        }
    }

    fun updateContact(contact: Contact) {
        viewModelScope.launch {
            repository.update(contact)
        }
    }

    fun savePathImage(pathImage: String) {
        pathImageMutableLiveData.postValue(pathImage)
    }

    fun isValidPhoneNumber(phoneNumber: String) {
        isValidPhoneNumberMutableLiveData.postValue(
            phoneNumber.length in 1..14 && isNotNumber(
                phoneNumber
            )
        )
    }

    private fun isNotNumber(string: String): Boolean {
        var i = 0
        while (i < string.length) {
            if ((string[i] < '0' || string[i] > '9') && string[i] != '+')
                return false
            i++
        }
        return true
    }
}