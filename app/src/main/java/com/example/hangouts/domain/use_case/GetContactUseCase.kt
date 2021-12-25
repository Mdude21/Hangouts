package com.example.hangouts.domain.use_case

import com.example.hangouts.domain.models.Contact
import com.example.hangouts.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow

class GetContactUseCase(
    private val repository: ContactRepository
) {

    operator fun invoke(): Flow<List<Contact>> {

    }
}