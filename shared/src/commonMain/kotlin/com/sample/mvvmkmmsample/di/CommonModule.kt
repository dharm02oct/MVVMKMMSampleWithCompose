package com.sample.mvvmkmmsample.di

import com.sample.mvvmkmmsample.DatabaseCreator
import com.sample.mvvmkmmsample.data.db.ContactDataSource
import com.sample.mvvmkmmsample.data.db.ContactDataSourceImpl
import com.sample.mvvmkmmsample.data.repository.ContactRepoImpl
import com.sample.mvvmkmmsample.domain.interactor.*
import com.sample.mvvmkmmsample.domain.repository.ContactsRepository
import org.koin.dsl.module


val commonUseCases = module{
    single { DeleteContact(get()) }
    single { GetContactById(get()) }
    single { GetContacts(get()) }
    single { GetContactsFiltered(get()) }
    single { SaveContact(get()) }
}

val dbModule = module {
    single<ContactDataSource> { ContactDataSourceImpl(DatabaseCreator.getDataBase(KoinInjector.mContextArgs)!!) }
    single<ContactsRepository> { ContactRepoImpl(get()) }
}







