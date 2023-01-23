package com.sample.mvvmkmmsample.data.db

import com.sample.mvvmkmmsample.data.db.model.DbContact
import com.sample.mvvmkmmsample.domain.model.Contact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


fun DbContact.toContact(): Contact = Contact(
        id = id,
        firstName = firstName,
        lastName = lastName,
        phone = phone,
        email = email
    )

    fun Contact.toDbContact(): DbContact = DbContact(
        id = id ,
        firstName = firstName,
        lastName = lastName,
        phone = phone,
        email = email
    )

   fun comsamplemvvmkmmsample.db.Contact.toModelContact() = Contact(
        id = id,
        firstName = firstname,
        lastName = lastname,
        phone = phone,
        email = email
    )

fun List<comsamplemvvmkmmsample.db.Contact>.toModelContactList(): List<Contact> = map {
    Contact(
        id = it.id,
        firstName = it.firstname,
        lastName = it.lastname,
        phone = it.phone,
        email = it.email
    )
}

fun Flow<List<comsamplemvvmkmmsample.db.Contact>>.toModelContactFlow():Flow<List<Contact>> = map { it ->
    it.map {
        Contact(
            id = it.id,
            firstName = it.firstname,
            lastName = it.lastname,
            phone = it.phone,
            email = it.email
        )
    }
}


