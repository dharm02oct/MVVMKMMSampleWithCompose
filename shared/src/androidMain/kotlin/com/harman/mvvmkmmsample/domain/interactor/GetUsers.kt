package com.harman.mvvmkmmsample.domain.interactor

import androidx.paging.PagingData
import com.harman.mvvmkmmsample.domain.model.User
import com.harman.mvvmkmmsample.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow

actual class GetUsers constructor(private val usersRepository: UsersRepository) :
    UseCase<Long?, Flow<PagingData<User>>>() {
    override suspend fun performAction(param: Long?) = usersRepository.getUsers(param)
}

