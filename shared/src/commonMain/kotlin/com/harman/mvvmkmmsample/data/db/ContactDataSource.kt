package com.harman.mvvmkmmsample.data.db

import com.harman.mvvmkmmsample.domain.model.Contact
import kotlinx.coroutines.flow.Flow


interface ContactDataSource {
 suspend fun saveContact(contact: com.harman.mvvmkmmsample.domain.model.Contact):Long
 suspend fun getContactById( id:Long): com.harman.mvvmkmmsample.domain.model.Contact
 suspend fun deleteContact(id:Long)
 suspend fun getAllContact(): List<com.harman.mvvmkmmsample.domain.model.Contact>
 suspend fun getFilteredContacts(filter:String): List<com.harman.mvvmkmmsample.domain.model.Contact>
  suspend fun  getFilteredContactsFLow(filter:String): Flow<List<com.harman.mvvmkmmsample.domain.model.Contact>>
 suspend fun getAllContactFlow(): Flow<List<Contact>>
}

 class ContactDataSourceImpl(private val database: Database) : ContactDataSource {
    override suspend fun saveContact(contact: com.harman.mvvmkmmsample.domain.model.Contact):Long {
       return database.saveContact(contact)
    }

    override suspend fun getContactById(id: Long): com.harman.mvvmkmmsample.domain.model.Contact {
        return database.getContactById(id).toModelContact()
    }

    override suspend fun deleteContact(id: Long) {
      return database.deleteContact(id)
    }

    override suspend fun getAllContact(): List<com.harman.mvvmkmmsample.domain.model.Contact> {
        return database.getAllContact().toModelContactList()
    }

    override suspend fun getFilteredContacts(filter: String): List<com.harman.mvvmkmmsample.domain.model.Contact> {
       return database.getFilteredContacts(filter).toModelContactList()
    }

     override suspend fun getFilteredContactsFLow(filter: String): Flow<List<Contact>> {
        return database.getFilteredContactsFlow(filter).toModelContactFlow()
     }

     override suspend fun getAllContactFlow(): Flow<List<Contact>> {
         return database.getAllContactFLow().toModelContactFlow()
     }
 }