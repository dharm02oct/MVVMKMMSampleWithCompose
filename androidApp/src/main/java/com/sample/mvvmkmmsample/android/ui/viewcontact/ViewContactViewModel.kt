package com.sample.mvvmkmmsample.android.ui.viewcontact

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.sample.mvvmkmmsample.domain.interactor.DeleteContact
import com.sample.mvvmkmmsample.domain.interactor.GetContactById
import com.sample.mvvmkmmsample.domain.model.Contact
import com.sample.mvvmkmmsample.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ViewContactViewModel(private val getContactById: GetContactById,private val deleteContact: DeleteContact) : BaseViewModel() {
    var contact = MutableStateFlow(Contact.getInstance())
    var isDeleted = MutableStateFlow(false)

    @SuppressLint("LongLogTag")
    fun getContact(id: Long) = viewModelScope.launch {
        if(id> 0) {
            _isLoading.value = true
            try {
                contact.value = getContactById.execute(id)!!
            }catch (ex:Exception){
                Log.d("ViewContactViewModel exception:", ex.toString())
            }finally {
                _isLoading.value = false
            }
        }
    }

    public fun deleteContact(){
        viewModelScope.launch{
            var id:Long? = contact.value.id
            if (id != null) {
                if(id>0){
                    isDeleted.value = false
                    try {
                        deleteContact.execute(id)
                        isDeleted.value = true
                    }catch (ex:Exception){
                    }finally {
                        isDeleted.value = true
                    }
                }
            }
        }
    }


}