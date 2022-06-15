package com.harman.mvvmkmmsample.domain.interactor

import com.harman.mvvmkmmsample.domain.model.Contact
import com.harman.mvvmkmmsample.domain.repository.ContactsRepository
import kotlinx.coroutines.flow.Flow

class GetContactsFiltered constructor(private val contactsRepository: ContactsRepository) :
    UseCase<String, Flow<List<Contact>>>() {
    override suspend fun performAction(param: String): Flow<List<Contact>> = contactsRepository.getFilteredContactsFlow(param)


}