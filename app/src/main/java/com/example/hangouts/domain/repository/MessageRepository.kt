package com.example.hangouts.domain.repository

import com.example.hangouts.domain.models.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepository {

    fun getAllMessages(id: Long): Flow<List<Message>>

    suspend fun addMessage(message: Message)

}