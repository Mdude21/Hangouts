package com.example.hangouts.data.data_source

import androidx.room.*
import com.example.hangouts.domain.models.Message
import kotlinx.coroutines.flow.Flow


@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: Message)

    @Query("SELECT * FROM messages WHERE contactId = :id ORDER BY time ASC")
    fun getAllMessagesByContactId(id : Long) : Flow<List<Message>>
}