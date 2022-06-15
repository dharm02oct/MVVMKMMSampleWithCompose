package com.harman.mvvmkmmsample.domain.interactor

import com.harman.mvvmkmmsample.domain.model.Contact
import com.harman.mvvmkmmsample.domain.repository.ContactsRepository

class SaveContact constructor(private val contactsRepository: ContactsRepository) :
    UseCase<Contact, Long>() {
    override suspend fun performAction(param: Contact) = contactsRepository.saveContact(param)
}