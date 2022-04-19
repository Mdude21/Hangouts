package com.example.hangouts.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = ContactContract.TABLE_NAME
)

data class Contact(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ContactContract.Columns.ID)
    var id: Long,
    @ColumnInfo(name = ContactContract.Columns.PHONE)
    var phoneNumber: String?,
    @ColumnInfo(name = ContactContract.Columns.FIRSTNAME)
    var firstName : String?,
    @ColumnInfo(name = ContactContract.Columns.LASTNAME)
    var lastName: String?,
    @ColumnInfo(name = ContactContract.Columns.EMAIL)
    var email: String?,
    @ColumnInfo(name = ContactContract.Columns.ADDRESS)
    var address: String?,
    @ColumnInfo(name = ContactContract.Columns.AVATAR)
    var avatar: String?
) : Serializable