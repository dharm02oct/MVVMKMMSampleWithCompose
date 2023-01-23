package com.sample.mvvmkmmsample.android.ui.contacts

import androidx.lifecycle.viewModelScope
import com.sample.mvvmkmmsample.domain.interactor.GetContacts
import com.sample.mvvmkmmsample.domain.interactor.GetContactsFiltered
import com.sample.mvvmkmmsample.domain.model.Contact
import com.sample.mvvmkmmsample.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class ContactListViewModel(
    private val getContacts: GetContacts,
    private val getContactsFiltered: GetContactsFiltered
) : BaseViewModel() {
    val dummyContacts: Flow<List<Contact>> = flowOf(
        listOf(
            Contact(122, "Divit", "M", 344343, "di@gmail.com"),
            Contact(122, "Dharam", "M", 344343, "dharr@gmail.com"),
            Contact(122, "Mina", "M", 344343, "mi@gmail.com")
        )
    )
   var stateFlowContacts = MutableStateFlow<List<Contact>>(listOf())
    fun getContacts() {
        viewModelScope.launch {
             getContacts.execute(PAGE_SIZE).collectLatest {
                 stateFlowContacts.value = it
             }
        }
    }

    fun filterContacts(filter: String) {
        viewModelScope.launch {
            getContactsFiltered.execute(filter).collectLatest {
                stateFlowContacts.value = it
            }
        }

    }

    private var filter: String = ""
    fun setValue(value: String) {
        filter = value.ifEmpty {
            ""
        }
        println("filter set : $filter")

    }

    fun onValueChange(filter: String) {
        //  stateFlowContacts
    }

    companion object SimpleObj {
        private const val PAGE_SIZE = 20
        val dummyContacts: List<Contact> =
            listOf(
                Contact(122, "Divit I", "Mishra", 344343, "di@gmail.com"),
                Contact(122, "Dharam I", "Mishra", 344343, "dharr@gmail.com"),
                Contact(122, "Mina I", "Mishra", 344343, "mi@gmail.com")
            )
    }
}

