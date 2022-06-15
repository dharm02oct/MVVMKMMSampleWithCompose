package com.harman.mvvmkmmsample.domain.interactor

import com.harman.mvvmkmmsample.domain.model.Contact
import com.harman.mvvmkmmsample.domain.repository.ContactsRepository
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow

class GetContacts constructor(private val contactsRepository: ContactsRepository) :
    UseCase<Int, Flow<List<Contact>>>() {
    private val dummyContacts : List<Contact> =
        listOf(Contact(122,"AAAAA","221",344343,"di@gmail.com"),
            Contact(122,"BBB","111",344343,"dharr@gmail.com"),
            Contact(122,"CCC","11",344343,"mi@gmail.com"),
            Contact(122,"DDD","111",344343,"di@gmail.com"),
            Contact(122,"EEE","1111",344343,"di@gmail.com"),
            Contact(122,"FFFF","1111",344343,"di@gmail.com")
            ,Contact(122,"JJJJ","111",344343,"di@gmail.com"))

    @OptIn(InternalCoroutinesApi::class)
    override suspend fun performAction(param: Int): Flow<List<Contact>> {
    /*    dummyContacts.forEach {
            contactsRepository.saveContact(it)
        }*/

          //contactsRepository.getAllContactFlow()

      return  contactsRepository.getAllContactFlow()
    }

}