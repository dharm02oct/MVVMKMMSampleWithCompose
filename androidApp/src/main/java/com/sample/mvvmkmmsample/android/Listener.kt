package com.sample.mvvmkmmsample.android

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.sample.mvvmkmmsample.domain.model.Contact
import com.sample.mvvmkmmsample.domain.repository.ContactsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Listener : BroadcastReceiver(),KoinComponent {

    private val repo by inject<ContactsRepository>()
   private val scope= CoroutineScope(Job())
    private val dummyContacts : List<Contact> =
        listOf(
            Contact(122,"Con1","221",344343,"di@gmail.com"),

        )
    override fun onReceive(context: Context?, intent: Intent?) {
            scope.launch(Dispatchers.IO) {
                    dummyContacts.forEach {
                        var id = repo.saveContact(it)
                        println("Contact inserted ID: $id")
       }
            }
        }
    }
