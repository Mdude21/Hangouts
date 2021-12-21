package com.example.hangouts.fragments.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hangouts.models.DisplayContactItem

class ListContactFragmentViewModel : ViewModel(){

//    private val repository = ContactRepository()

    private val contactListLiveData = MutableLiveData<List<DisplayContactItem>>(
        listOf(
            DisplayContactItem(
                name = "asd",
                text = "qwe",
                avatar = "zxc"
            ),
            DisplayContactItem(
                name = "Ivan Ivanov",
                text = "hi",
                avatar = "lol"
            )
        )
    )

    val contactList: LiveData<List<DisplayContactItem>>
        get() = contactListLiveData



}