package com.example.hangouts.domain.repository

import com.example.hangouts.domain.models.Message

interface MessageRepository {

    suspend fun getAllMessages(id : Long) : List<Message>

    suspend fun addMessage(message: Message)

}