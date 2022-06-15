package com.harman.mvvmkmmsample.domain.repository

import com.harman.mvvmkmmsample.domain.model.Contact
import kotlinx.coroutines.flow.Flow

interface ContactsRepository : Repository {
   suspend fun saveContact(contact:Contact):Long
   suspend  fun getContactById(id:Long):Contact
   suspend fun deleteContact(id:Long)
   suspend fun getAllContact(): List<Contact>
   suspend fun getFilteredContacts(filter:String) :List<Contact>
   suspend fun getFilteredContactsFlow(filter:String) :Flow<List<Contact>>
   suspend fun getAllContactFlow(): Flow<List<Contact>>
}