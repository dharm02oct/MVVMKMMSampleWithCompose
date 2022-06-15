package com.harman.mvvmkmmsample.di

import com.harman.mvvmkmmsample.DatabaseCreator
import com.harman.mvvmkmmsample.data.db.ContactDataSource
import com.harman.mvvmkmmsample.data.db.ContactDataSourceImpl
import com.harman.mvvmkmmsample.data.repository.ContactRepoImpl
import com.harman.mvvmkmmsample.domain.interactor.*
import com.harman.mvvmkmmsample.domain.repository.ContactsRepository
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







