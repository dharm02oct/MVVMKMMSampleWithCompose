package com.harman.mvvmkmmsample.data.db

import com.harman.mvvmkmmsample.data.db.model.DbContact
import com.harman.mvvmkmmsample.domain.model.Contact
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

   fun comharmanmvvmkmmsample.db.Contact.toModelContact() = Contact(
        id = id,
        firstName = firstname,
        lastName = lastname,
        phone = phone,
        email = email
    )

fun List<comharmanmvvmkmmsample.db.Contact>.toModelContactList(): List<Contact> = map {
    Contact(
        id = it.id,
        firstName = it.firstname,
        lastName = it.lastname,
        phone = it.phone,
        email = it.email
    )
}

fun Flow<List<comharmanmvvmkmmsample.db.Contact>>.toModelContactFlow():Flow<List<Contact>> = map { it ->
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


