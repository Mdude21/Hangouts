package com.example.hangouts.models

import java.util.*

data class ContactItem(
    val uuid: UUID,
    val firstName : String?,
    val secondName: String?,
    val phoneNumber: String,
    val avatar: String
)