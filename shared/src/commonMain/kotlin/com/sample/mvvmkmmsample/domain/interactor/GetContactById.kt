package com.sample.mvvmkmmsample.domain.interactor

import com.sample.mvvmkmmsample.domain.model.Contact
import com.sample.mvvmkmmsample.domain.repository.ContactsRepository


class GetContactById constructor(private val contactsRepository: ContactsRepository) :
    UseCase<Long, Contact?>() {
    override suspend fun performAction(param: Long) = contactsRepository.getContactById(param)
}