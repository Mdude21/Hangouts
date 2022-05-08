package com.example.hangouts.ui.viewmodels

import android.telephony.SmsManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hangouts.data.repository.MessageRepositoryImpl
import com.example.hangouts.domain.models.Message
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect


class SMSViewModel : ViewModel() {
    private val repository = MessageRepositoryImpl()

    private val messageListLiveData = MutableLiveData<List<Message>>()

    val messageList: LiveData<List<Message>>
        get() = messageListLiveData

    fun addMessage(message: Message) {
        viewModelScope.launch {
            repository.addMessage(message)
        }
    }

    fun getAllMessages(id: Long) {
        viewModelScope.launch {
            repository.getAllMessages(id).collect {
                messageListLiveData.postValue(it)
            }
        }
    }
}