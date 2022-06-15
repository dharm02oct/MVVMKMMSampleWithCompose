package com.harman.mvvmkmmsample.domain.repository

import androidx.paging.PagingData
import com.harman.mvvmkmmsample.domain.model.User
import kotlinx.coroutines.flow.Flow

actual interface UsersRepository : Repository {
    suspend fun getUsers(lastId: Long?): Flow<PagingData<User>>
}