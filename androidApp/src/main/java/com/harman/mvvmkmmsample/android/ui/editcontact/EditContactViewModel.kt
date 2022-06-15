package com.harman.mvvmkmmsample.android.ui.editcontact

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.harman.mvvmkmmsample.android.ui.MutableLiveEvent
import com.harman.mvvmkmmsample.android.ui.Validators
import com.harman.mvvmkmmsample.android.vo.StateEvent
import com.harman.mvvmkmmsample.domain.interactor.GetContactById
import com.harman.mvvmkmmsample.domain.interactor.SaveContact
import com.harman.mvvmkmmsample.domain.model.Contact
import com.harman.mvvmkmmsample.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class EditContactViewModel constructor(
    private val getContactById: GetContactById,
    private val saveContact: SaveContact
) : BaseViewModel() {
    var contactId: Long? = null
    val firstname = MutableLiveData<String>()
    val lastname = MutableLiveData<String>()
    val phone = MutableLiveData<Long>()
    val email = MutableLiveData<String>()

    val firstnameError = firstname.map { Validators.required(it) }
    val phoneError = phone.map { Validators.phone(it, true) }
    val emailError = email.map { Validators.email(it, true) }
    private val _isFormValid = MediatorLiveData<Boolean>().apply {
        listOf(firstnameError, phoneError, emailError).forEach { source ->
            addSource(source) { this.value = validateForm() }
        }
    }
    val isFormValid: LiveData<Boolean>
        get() = _isFormValid

    private val _savedEvent = MutableLiveEvent<StateEvent<Long>>()
    val savedEvent: LiveData<StateEvent<Long>>
        get() = _savedEvent

    private fun validateForm(): Boolean {
        return Validators.required(firstname.value) == null
                && Validators.phone(phone.value, true) == null
                && Validators.email(email.value, true) == null
    }

    fun setContact(id: Long) {
        if (id > 0) {
            viewModelScope.launch {
                _isLoading.value = true
                try {
                    getContactById.execute(id)?.let {
                        contactId = it.id
                        firstname.value = it.firstName
                        lastname.value = it.lastName
                        phone.value?: 1
                        email.value = it.email
                    }
                } catch (ex: Exception) {
                } finally {
                    _isLoading.value = false
                }
            }
        }
    }

    //@RequiresApi(Build.VERSION_CODES.M)
    @RequiresApi(Build.VERSION_CODES.M)
    fun saveContact() {
        if (isFormValid.value == false) {
            return
        }
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val contact = contactId?.let {
                    Contact(
                        it,
                        firstname.value ?: "",
                        lastname.value ?: "",
                        phone.value?:1,
                        email.value ?: ""
                    )
                }?: Contact(
                    id = null,
                    firstname.value ?: "",
                    lastname.value ?: "",
                    phone.value?:1,
                    email.value ?: ""
                )
                val id = saveContact.execute(contact?: Contact.getInstance())
                println("save contact: $contact")
                _savedEvent.value = StateEvent.success(id)
            } catch (ex: Exception) {
                _savedEvent.value = StateEvent.error()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
