package com.sample.mvvmkmmsample.data.db

import com.sample.mvvmkmmsample.db.ContactDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import comsamplemvvmkmmsample.db.Contact
import kotlinx.coroutines.flow.Flow

class Database(sqlDriver: SqlDriver) {

    private val database = ContactDatabase(sqlDriver)
    private val dbQuery = database.contactDatabaseQueries


    fun saveContact(contact: com.sample.mvvmkmmsample.domain.model.Contact):Long {
         dbQuery.saveContact(contact.firstName, contact.lastName, contact.phone, contact.email)
        return   dbQuery.selectLastInsertedRowId().executeAsOne()
    }

    fun getContactById(id: Long): Contact {
        return dbQuery.getContactById(id).executeAsOne()
    }

    fun deleteContact(id: Long) {
        return dbQuery.deleteContact(id)
    }

    fun getAllContact(): List<Contact> {
        return dbQuery.getAllContact().executeAsList()
    }

    fun getFilteredContacts(filter: String): List<Contact> =
        dbQuery.filterContact(filter).executeAsList()

    fun getAllContactFLow(): Flow<List<Contact>> {
       return dbQuery.getAllContact().asFlow().mapToList()
    }

    fun getFilteredContactsFlow(filter: String): Flow<List<Contact>> =
        dbQuery.filterContact(filter).asFlow().mapToList()

}