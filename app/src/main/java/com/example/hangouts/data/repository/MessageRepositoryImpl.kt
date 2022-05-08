package com.example.hangouts.data.repository

import com.example.hangouts.data.DataBase
import com.example.hangouts.domain.models.Message
import com.example.hangouts.domain.repository.MessageRepository
import kotlinx.coroutines.flow.Flow

class MessageRepositoryImpl : MessageRepository {

    private val dao = DataBase.instance.MessageDao()

    override fun getAllMessages(id: Long): Flow<List<Message>> {
        return dao.getAllMessagesByContactId(id)
    }

    override suspend fun addMessage(message: Message) {
        dao.insertMessage(message)
    }
}