package com.harman.mvvmkmmsample.data.repository

import com.harman.mvvmkmmsample.data.db.ContactDataSource
import com.harman.mvvmkmmsample.domain.model.Contact
import com.harman.mvvmkmmsample.domain.repository.ContactsRepository
import kotlinx.coroutines.flow.Flow


class ContactRepoImpl(private val dao: ContactDataSource):ContactsRepository {
    override suspend fun saveContact(contact: com.harman.mvvmkmmsample.domain.model.Contact) : Long{
     return dao.saveContact(contact)
    }

    override suspend fun getContactById(id: Long):com.harman.mvvmkmmsample.domain.model.Contact {
        return dao.getContactById(id)
    }

    override suspend fun deleteContact(id: Long) {
       dao.deleteContact(id)
    }

    override suspend fun getAllContact(): List<com.harman.mvvmkmmsample.domain.model.Contact> {
        return dao.getAllContact()
    }

    override suspend fun getFilteredContacts(filter:String): List<com.harman.mvvmkmmsample.domain.model.Contact> {
        return dao.getFilteredContacts(filter)
    }

    override suspend fun getFilteredContactsFlow(filter:String):Flow<List<com.harman.mvvmkmmsample.domain.model.Contact>> {
        return dao.getFilteredContactsFLow(filter)
    }

    override suspend fun getAllContactFlow(): Flow<List<Contact>> {
      return dao.getAllContactFlow()
    }



    override fun clear() {
      println("DB Cleared ")
    }
}