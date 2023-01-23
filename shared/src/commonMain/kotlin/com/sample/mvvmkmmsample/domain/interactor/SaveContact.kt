package com.sample.mvvmkmmsample.domain.interactor

import com.sample.mvvmkmmsample.domain.model.Contact
import com.sample.mvvmkmmsample.domain.repository.ContactsRepository

class SaveContact constructor(private val contactsRepository: ContactsRepository) :
    UseCase<Contact, Long>() {
    override suspend fun performAction(param: Contact) = contactsRepository.saveContact(param)
}