package com.example.hangouts.data.repository

import com.example.hangouts.data.DataBase
import com.example.hangouts.domain.models.Message
import com.example.hangouts.domain.repository.MessageRepository

class MessageRepositoryImpl : MessageRepository {

    private val dao = DataBase.instance.MessageDao()

    override suspend fun getAllMessages(id: Long): List<Message> {
        return dao.getAllMessagesByContactId(id)
    }

    override suspend fun addMessage(message: Message) {
        dao.insertMessage(message)
    }
}