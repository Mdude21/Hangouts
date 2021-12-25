package com.example.hangouts.ui.fragments.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hangouts.domain.models.DisplayContactItem

class ListContactFragmentViewModel : ViewModel(){

    private val repository = ContactRepository()

    private val contactListLiveData = MutableLiveData<List<DisplayContactItem>>()

    val contactList: LiveData<List<DisplayContactItem>>
        get() = contactListLiveData



}