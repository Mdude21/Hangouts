package com.example.hangouts.data.data_source

import androidx.room.*
import com.example.hangouts.domain.models.Message


@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: Message)

    @Query("SELECT * FROM messages WHERE contactId = :id ORDER BY time ASC")
    suspend fun getAllMessagesByContactId(id : Long) : List<Message>
}