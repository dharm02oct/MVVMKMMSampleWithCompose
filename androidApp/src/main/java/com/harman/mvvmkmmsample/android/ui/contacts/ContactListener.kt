package com.harman.mvvmkmmsample.android.ui.contacts

interface ContactListener {
    fun call(phone: Long)
    fun open(contactId: Long?)
}