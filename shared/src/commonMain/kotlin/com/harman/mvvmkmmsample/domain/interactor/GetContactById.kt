package com.harman.mvvmkmmsample.domain.interactor

import com.harman.mvvmkmmsample.domain.model.Contact
import com.harman.mvvmkmmsample.domain.repository.ContactsRepository


class GetContactById constructor(private val contactsRepository: ContactsRepository) :
    UseCase<Long, Contact?>() {
    override suspend fun performAction(param: Long) = contactsRepository.getContactById(param)
}