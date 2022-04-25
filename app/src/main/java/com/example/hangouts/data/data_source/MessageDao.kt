package com.example.hangouts.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hangouts.domain.models.Message


@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: Message)

    @Query("SELECT * FROM messages WHERE contactId = :id ORDER BY time ASC")
    suspend fun getAllMessagesByContactId(id : Long) : List<Message>
}