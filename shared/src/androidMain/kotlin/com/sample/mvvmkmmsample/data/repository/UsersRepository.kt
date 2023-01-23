package com.sample.mvvmkmmsample.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sample.mvvmkmmsample.data.net.GitHubUsersApi
import com.sample.mvvmkmmsample.domain.model.User
import com.sample.mvvmkmmsample.domain.repository.UsersRepository
import io.ktor.client.*
import kotlinx.coroutines.flow.Flow

actual class UsersRepository constructor(private val api: GitHubUsersApi, private val client: HttpClient, private val userName:String) : UsersRepository{
    override suspend fun getUsers(lastId: Long?): Flow<PagingData<User>> {
        val pagingSource = UsersPagingSource(api,client,userName)
        return Pager(
            config = createPagingConfig(),
            pagingSourceFactory = { pagingSource }
        ).flow
    }

    private fun createPagingConfig() =
        PagingConfig(pageSize = 1, enablePlaceholders = false)


    override fun clear() {
      println("cleared")
    }



}