package com.example.hangouts.domain.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "messages", foreignKeys = [
    ForeignKey(
        entity = Contact::class,
        parentColumns = ["id"],
        childColumns = ["contactId"],
        onDelete = CASCADE
    )
])
data class Message(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val message: String?,
    val time: Long,
    val user: String?,
    val type: Int,
    val contactId: Long
)
