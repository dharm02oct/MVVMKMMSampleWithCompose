package com.sample.mvvmkmmsample.domain.interactor

import com.sample.mvvmkmmsample.domain.repository.ContactsRepository


class DeleteContact constructor(private val contactsRepository: ContactsRepository) :
    UseCase<Long, Unit>() {
    override suspend fun performAction(param: Long) = contactsRepository.deleteContact(param)
}