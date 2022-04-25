package com.example.hangouts.ui.viewmodels

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hangouts.data.repository.MessageRepositoryImpl
import com.example.hangouts.domain.models.Message
import kotlinx.coroutines.launch
import androidx.core.content.ContextCompat.startActivity




class SMSViewModel : ViewModel() {
    private val repository = MessageRepositoryImpl()

    private val messageListLiveData = MutableLiveData<List<Message>>()

    val messageList : LiveData<List<Message>>
        get() = messageListLiveData

    fun addMessage(message: Message){
        viewModelScope.launch {
            repository.addMessage(message)
        }
    }

    fun getAllMessages(id : Long) {
        viewModelScope.launch {
            messageListLiveData.postValue(repository.getAllMessages(id))
        }
    }

    fun sendSMS(context: Context, message : String, number: String) {
        val uri = Uri.parse("smsto:$number")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        intent.putExtra("sms_body", message)
        context.startActivity(intent)
    }
}