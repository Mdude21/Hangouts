package com.example.hangouts.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.util.Log
import com.example.hangouts.data.repository.ContactRepositoryImpl
import com.example.hangouts.data.repository.MessageRepositoryImpl
import com.example.hangouts.domain.models.Contact
import com.example.hangouts.domain.models.Message
import com.example.hangouts.ui.adapters.SMSAdapter
import kotlinx.coroutines.*
import java.util.*

class SMSBroadcastReceiver : BroadcastReceiver() {

    private val contactRepository = ContactRepositoryImpl()
    private val messageRepository = MessageRepositoryImpl()
    private var contact: Contact? = null
    private var job : Job? = null

    override fun onReceive(context: Context, intent: Intent?) {
        if (!intent?.action.equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) return
        val extractMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
        extractMessages.forEach { smsMassage ->
            job?.cancel()
            job = GlobalScope.launch {
                val phone = smsMassage.displayOriginatingAddress

                contact = contactRepository.getContactByPhoneNumber(phone)
                if (contact == null) {
                    val contact = Contact(
                        id = Random().nextLong(),
                        phoneNumber = phone,
                        firstName = phone,
                        lastName = "",
                        email = null,
                        address = null,
                        avatar = null
                    )
                    addContact(contact)

                }
                addMessage(contact!!, smsMassage.displayMessageBody)
            }


        }
    }


    private suspend fun addContact(contact: Contact) {
        contactRepository.insertContact(contact)
    }

    private suspend fun addMessage(contact: Contact, text: String?) {
        val message = Message(
            id = Random().nextLong(),
            message = text,
            time = System.currentTimeMillis(),
            user = contact.firstName,
            type = 2,
            contactId = contact.id!!
        )

        messageRepository.addMessage(message)
    }

}