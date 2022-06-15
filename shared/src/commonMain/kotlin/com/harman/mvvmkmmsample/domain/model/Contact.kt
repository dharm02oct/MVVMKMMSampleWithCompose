package com.harman.mvvmkmmsample.domain.model

data class Contact(val id:Long? ,var firstName:String , var lastName:String , var phone:Long ,var email:String){

    companion object EmptyContact {
        fun getInstance(): Contact = Contact(12,"AAAA","BBB",1242142,"dharam@gmail.com")
    }
}


