package com.example.hangouts.domain.models

object ContactContract {

    const val TABLE_NAME = "contacts"

    object Columns {
        const val ID = "id"
        const val FIRSTNAME = "firstname"
        const val LASTNAME = "lastname"
        const val PHONE = "phone_number"
        const val EMAIL = "email"
        const val ADDRESS = "address"
        const val AVATAR = "avatar"
    }
}