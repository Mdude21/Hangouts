package com.example.hangouts.domain.models

data class Message(
    val id : Long,
    val message: String?,
    val time: Long,
    val user: String?,
    val type: Int
)
