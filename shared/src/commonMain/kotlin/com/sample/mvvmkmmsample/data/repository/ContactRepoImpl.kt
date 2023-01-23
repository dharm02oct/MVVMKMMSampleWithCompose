package com.sample.mvvmkmmsample.data.repository

import com.sample.mvvmkmmsample.data.db.ContactDataSource
import com.sample.mvvmkmmsample.domain.model.Contact
import com.sample.mvvmkmmsample.domain.repository.ContactsRepository
import kotlinx.coroutines.flow.Flow


class ContactRepoImpl(private val dao: ContactDataSource):ContactsRepository {
    override suspend fun saveContact(contact: com.sample.mvvmkmmsample.domain.model.Contact) : Long{
     return dao.saveContact(contact)
    }

    override suspend fun getContactById(id: Long):com.sample.mvvmkmmsample.domain.model.Contact {
        return dao.getContactById(id)
    }

    override suspend fun deleteContact(id: Long) {
       dao.deleteContact(id)
    }

    override suspend fun getAllContact(): List<com.sample.mvvmkmmsample.domain.model.Contact> {
        return dao.getAllContact()
    }

    override suspend fun getFilteredContacts(filter:String): List<com.sample.mvvmkmmsample.domain.model.Contact> {
        return dao.getFilteredContacts(filter)
    }

    override suspend fun getFilteredContactsFlow(filter:String):Flow<List<com.sample.mvvmkmmsample.domain.model.Contact>> {
        return dao.getFilteredContactsFLow(filter)
    }

    override suspend fun getAllContactFlow(): Flow<List<Contact>> {
      return dao.getAllContactFlow()
    }



    override fun clear() {
      println("DB Cleared ")
    }
}